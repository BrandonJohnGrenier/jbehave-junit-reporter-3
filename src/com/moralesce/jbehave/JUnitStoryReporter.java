package com.moralesce.jbehave;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;

public class JUnitStoryReporter extends AbstractStoryReporter {

	private RunNotifier notifier;
	private Description suiteDescription;
	Description scenario = null;
	Description step = null;

	private static final JUnitStoryReporter INSTANCE = new JUnitStoryReporter();

	public static JUnitStoryReporter getInstance() {
		return INSTANCE;
	}

	private JUnitStoryReporter() {
	}

	void setNotifier(RunNotifier notifier) {
		this.notifier = notifier;
	}

	void setDescription(Description description) {
		this.suiteDescription = description;
	}

	@Override
	public void beforeScenario(String key) {
		super.beforeScenario(key);

		this.scenario = suiteDescription.getChildren().get(storyCounter - 1).getChildren().get(scenarioCounter - 1);
	}

	@Override
	public void afterScenario() {
		super.afterScenario();

		this.scenario = null;
	}

	@Override
	public void beforeStep(String key) {
		super.beforeStep(key);

		step = scenario.getChildren().get(stepCounter - 1);

		this.notifier.fireTestStarted(step);
	}

	@Override
	public void failed(String key, Throwable cause) {
		super.failed(key, cause);

		this.notifier.fireTestFailure(new Failure(step, cause));
		step = null;
	}

	@Override
	public void pending(String key) {
		super.pending(key);

		this.notifier.fireTestIgnored(step);
		step = null;
	}

	@Override
	public void successful(String key) {
		super.successful(key);

		this.notifier.fireTestFinished(step);
		step = null;
	}
}
