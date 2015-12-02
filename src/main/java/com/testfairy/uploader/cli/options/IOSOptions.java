package com.testfairy.uploader.cli.options;

import com.testfairy.uploader.IOSUploader;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

public interface IOSOptions {
	void configure(OptionParser parser);
	void apply(OptionSet arguments, IOSUploader.Builder ios);
}
