package com.testfairy.uploader.cli.options;

import com.testfairy.uploader.AndroidUploader;
import com.testfairy.uploader.IOSUploader;
import com.testfairy.uploader.Metrics;
import com.testfairy.uploader.Options;
import com.testfairy.uploader.cli.StringUtils;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;

public class MetricsOption implements AndroidOptions, IOSOptions {

	private OptionSpec<String> metrics;

	@Override
	public void configure(OptionParser parser) {
		this.metrics = parser.accepts("metrics", "Comma-separated list of metrics to record").withRequiredArg();
	}

	@Override
	public void apply(OptionSet arguments, AndroidUploader.Builder android) {
		android.setMetrics(extract(arguments));
	}

	@Override
	public void apply(OptionSet arguments, IOSUploader.Builder ios) {
		ios.setMetrics(extract(arguments));
	}

	private Metrics extract(OptionSet optionSet) {
		if (! optionSet.has(this.metrics))
			return null;

		String all = optionSet.valueOf(this.metrics);
		if (StringUtils.isEmpty(all))
			return null;

		return new Metrics.Builder().addAll(all).build();
	}
}
