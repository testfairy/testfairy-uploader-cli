package com.testfairy.uploader.cli.options;

import com.testfairy.uploader.Options;
import com.testfairy.uploader.cli.StringUtils;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;

public class CustomOption implements OptionsArg {
	private OptionSpec<String> custom;

	@Override
	public void configure(OptionParser parser) {
		custom = parser.accepts("custom", "Additional fields uploaded with build. E.g. --custom=a=b,c=d,e=f").withRequiredArg();
	}

	@Override
	public Options.Builder apply(OptionSet optionSet, Options.Builder builder) {
		if (! optionSet.has(custom))
			return builder;

		String value = optionSet.valueOf(custom);
		if (StringUtils.isEmpty(value))
			return builder;

		return addCustomField(builder, value);
	}

	private Options.Builder addCustomField(Options.Builder builder, String value) {
		if (builder == null)
			return new Options.Builder().setCustomFields(value);

		return builder.setCustomFields(value);
	}
}
