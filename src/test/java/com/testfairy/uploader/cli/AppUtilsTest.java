package com.testfairy.uploader.cli;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;

@RunWith(MockitoJUnitRunner.class)
public class AppUtilsTest {
	@Test
	public void isAndroidAPK_returns_true_when_files_contains_expected_files() {
		Assert.assertEquals(true, AppUtils.isAndroidAPK(Arrays.asList(
			"AndroidManifest.xml",
			"classes.dex"
		)));
	}

	@Test
	public void isAndroidAPK_returns_false_when_AndroidManifest_is_not_found() {
		Assert.assertEquals(false, AppUtils.isAndroidAPK(Arrays.asList(
			"classes.dex"
		)));
	}

	@Test
	public void isAndroidAPK_returns_false_when_classes_is_not_found() {
		Assert.assertEquals(false, AppUtils.isAndroidAPK(Arrays.asList(
			"AndroidManifest.xml"
		)));
	}

	@Test
	public void isAppleIPA_returns_true_when_files_contains_expected_files() {
		Assert.assertEquals(true, AppUtils.isAppleIPA(Collections.singletonList(
			"Payload/blah/build.app/"
		)));
	}

	@Test
	public void isAppleIPA_returns_false_when_Payload_directory_not_found() {
		Assert.assertEquals(false, AppUtils.isAppleIPA(Collections.singletonList(
			"build.app"
		)));
	}

	@Test
	public void isAppleIPA_returns_false_when_app_directory_not_found() {
		Assert.assertEquals(false, AppUtils.isAppleIPA(Collections.singletonList(
			"Payload/blah/build.ipa"
		)));
	}
}
