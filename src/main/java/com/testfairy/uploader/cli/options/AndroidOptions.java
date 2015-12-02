package com.testfairy.uploader.cli.options;

import com.testfairy.uploader.AndroidUploader;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;

public interface AndroidOptions {
	void configure(OptionParser parser);
	void apply(OptionSet arguments, AndroidUploader.Builder android);
}
