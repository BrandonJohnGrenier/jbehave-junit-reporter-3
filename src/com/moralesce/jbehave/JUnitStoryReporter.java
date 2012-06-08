package com.moralesce.jbehave;

import org.jbehave.core.reporters.StoryReporter;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;

public class JUnitStoryReporter extends AbstractStoryReporter implements StoryReporter {

	private RunNotifier notifier;
	private Description storyDescription;
	private Description scenario = null;

	private static Object lock = new Object();
	private static JUnitStoryReporter instance = null;

	public static JUnitStoryReporter getInstance() {
		synchronized (lock) {
			if (instance == null) {
				instance = new JUnitStoryReporter();
			}
			return instance;
		}
	}

	public static void withRunNotifier(RunNotifier notifier) {
		getInstance().setNotifier(notifier);
	}

	public static void withDescription(Description description) {
		getInstance().setDescription(description);
	}

	private JUnitStoryReporter() {

	}

	private void setNotifier(RunNotifier notifier) {
		this.notifier = notifier;
	}

	private void setDescription(Description description) {
		this.storyDescription = description;
	}

	public void beforeScenario(String key) {
		for (Description description : storyDescription.getChildren()) {
			if (description.getDisplayName().contains(key)) {
				this.scenario = description;
			}
		}
	}

	public void afterScenario() {
		this.scenario = null;
	}

	public void failed(String key, Throwable t) {
		Description description = resolveStepDescription(key);
		if (description != null) {
			this.notifier.fireTestStarted(description);
			this.notifier.fireTestFailure(new Failure(description, t));
		}
	}

	public void pending(String key) {
		Description description = resolveStepDescription(key);
		if (description != null) {
			this.notifier.fireTestIgnored(description);
		}
	}

	public void successful(String key) {
		Description description = resolveStepDescription(key);
		if (description != null) {
			this.notifier.fireTestStarted(description);
			this.notifier.fireTestFinished(description);
		}
	}

	private Description resolveStepDescription(String key) {
		key = key.replaceAll("[^\\w\\s\\p{Punct}]", "");
		for (Description description : scenario.getChildren()) {
			if (getStepName(description).equals(key)) {
				return description;
			}
		}
		return null;
	}

	private String getStepName(Description stepDescription) {
		String stepName = stripClassNameFromDisplayName(stepDescription);
		int i = stepName.indexOf(" - ", 0);
		return stepName.substring(i + 3, stepName.length()).trim();
	}

	private String stripClassNameFromDisplayName(Description stepDescription) {
		return stepDescription.getDisplayName().split("\\(")[0];
	}

}
