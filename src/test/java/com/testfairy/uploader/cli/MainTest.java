package com.testfairy.uploader.cli;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MainTest {
	private static final String API_KEY = "7ac85171ea92ad548396846dc4310e1e625fe65a";
	private static final String IPA_PATH = MainTest.class.getClassLoader().getResource("test-ipa.ipa").getPath();
	private static final String APK_PATH = MainTest.class.getClassLoader().getResource("test-apk.apk").getPath();
	private static final String INVALID_FILE = MainTest.class.getClassLoader().getResource("invalid-file.txt").getPath();
	private static final String KEYSTORE_PATH = MainTest.class.getClassLoader().getResource("debug.keystore").getPath();
	private static final String COMMIT_MESSAGE_FILE_PATH = MainTest.class.getClassLoader().getResource("commit.txt").getPath();
	private static final String KEYSTORE_ALIAS = "androiddebugkey";
	private static final String KEYSTORE_PASSWORD = "android";

	@Test
	public void returns_error_when_file_does_not_exist() {
		assertEquals(Main.EXIT_NO_API_KEY, run("/Users/downloads/SampleApp.apk"));
	}

	@Test
	public void returns_when_no_file_is_given() {
		assertEquals(Main.EXIT_NO_API_KEY, run(""));
	}

	@Test
	public void returns_when_no_file_is_given_but_contains_arguments() {
		assertEquals(Main.EXIT_NO_ARTIFACT_FILE, run("--api-key=" + API_KEY));
	}

	@Test
	public void returns_error_when_file_does_not_exist_but_contains_arguments() {
		assertEquals(Main.EXIT_INVALID_ARTIFACT_FILE, run("--api-key=" + API_KEY + " /Users/downloads/SampleApp.apk"));
	}

	@Test
	public void returns_error_when_file_is_invalid() {
		assertEquals(Main.EXIT_INVALID_ARTIFACT_FILE_TYPE, run("--api-key=" + API_KEY + " " + INVALID_FILE));
	}

	@Test
	public void returns_error_when_api_key_is_missing() {
		assertEquals(Main.EXIT_NO_API_KEY, run(IPA_PATH));
	}

	@Test
	public void returns_error_when_api_key_is_empty() {
		assertEquals(Main.EXIT_NO_ARTIFACT_FILE, run("--api-key " + IPA_PATH));
	}

	@Test
	public void returns_success_when_viewing_usage() {
		assertEquals(Main.EXIT_SUCCESS, run("--help"));
	}

	@Test
	public void returns_success_when_viewing_version() {
		assertEquals(Main.EXIT_SUCCESS, run("--version"));
	}

	@Test
	public void returns_error_when_watermarking_ios_builds() {
		assertEquals(Main.EXIT_UNEXPECTED_EXCEPTION, run("--watermark --api-key=" + API_KEY + " " + IPA_PATH));
	}

	@Test
	public void returns_error_when_anonymous_ios_builds() {
		assertEquals(Main.EXIT_UNEXPECTED_EXCEPTION, run("--anonymous --api-key=" + API_KEY + " " + IPA_PATH));
	}

	@Test
	public void can_upload_ios_builds() {
		assertEquals(Main.EXIT_SUCCESS, run("--api-key=" + API_KEY + " " + IPA_PATH));
	}

	@Test
	public void can_upload_android_builds_signed() {
		assertEquals(Main.EXIT_SUCCESS, new Main().execute(new String[] {
			"--watermark",
			"--video=on",
			"--video-quality=high",
			"--tester-group=group",
			"--anonymous",
			"--auto-update",
			"--max-duration=20m",
			"--metrics=cpu,memory,opengl,memory,network,phone-signal,logcat,gps,battery,mic,wifi",
			"--notify",
			"--video-rate=1.0",
			"--instrumentation=on",
			"--keystore=" + KEYSTORE_PATH,
			"--alias=" + KEYSTORE_ALIAS,
			"--storepass=" + KEYSTORE_PASSWORD,
			"--comment=\"Cowabunga\"",
			"--custom=field1=value1,field2=value2",
			"--api-key=" + API_KEY,
			APK_PATH
		}));
	}

	@Test
	public void can_upload_android_builds_unsigned() {
		assertEquals(Main.EXIT_SUCCESS, run("--instrumentation=off --comment=@" + COMMIT_MESSAGE_FILE_PATH + " --metrics=memory,cpu,network --api-key=" + API_KEY + " " + APK_PATH));
	}

	private int run(String input) {
		return new Main().execute(input.split(" "));
	}
}
