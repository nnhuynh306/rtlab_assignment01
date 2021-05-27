package vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.XMLParser;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class InstanceIDParser {
    // We don't use namespaces
    private static final String ns = null;

    public String parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return findInstanceID(parser);
        } finally {
            in.close();
        }
    }

    private String findInstanceID(XmlPullParser parser) throws XmlPullParserException, IOException {
        String instanceID = null;

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals("meta")) {
                instanceID = readInstanceID(parser);
            } else {
                skip(parser);
            }
        }
        return instanceID;
    }

    private String readInstanceID(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "meta");
        String instanceID = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("instanceID")) {
                instanceID = readText(parser);
            } else if (name.equals("instanceName")) {
            } else {
                skip(parser);
            }
        }
        return instanceID;
    }

    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    public class Meta {
        public String instanceID;
        public String instanceName;

        public Meta(String instanceID, String instanceName) {
            this.instanceID = instanceID;
            this.instanceName = instanceName;
        }
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }

}
