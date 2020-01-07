package com.moralesce.jbehave;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;

public class JUnitStoryReporter extends AbstractStoryReporter {

	private static final String LOG_PROPERTY = "jbehave.log";

	String scenarioKey = null;
	Description scenario = null;
	Description step = null;

	private static final JUnitStoryReporter INSTANCE = new JUnitStoryReporter();

	public static JUnitStoryReporter getInstance() {
		return INSTANCE;
	}

	private JUnitStoryReporter() {
	}

	@Override
	public void beforeScenario(String key) {
		super.beforeScenario(key);

		scenarioKey = key;
		this.scenario = suiteDescription.getChildren().get(storyCounter - 1).getChildren().get(scenarioCounter - 1);

		if (Boolean.valueOf(System.getProperty(LOG_PROPERTY))) {
			System.out.println("Starting scenario " + scenarioKey + " ...");
		}
	}

	@Override
	public void afterScenario() {
		super.afterScenario();

		if (Boolean.valueOf(System.getProperty(LOG_PROPERTY))) {
			System.out.println("Scenario ended " + scenarioKey);
		}

		this.scenario = null;
		scenarioKey = null;
	}

	@Override
	public void beforeStep(String key) {
		super.beforeStep(key);

		step = scenario.getChildren().get(stepCounter - 1);

		this.notifier.fireTestStarted(step);
		if (Boolean.valueOf(System.getProperty(LOG_PROPERTY))) {
			System.out.println("Stepping " + key + " ...");
		}
	}

	@Override
	public void failed(String key, Throwable cause) {
		super.failed(key, cause);

		if (step == null) {
			step = scenario.getChildren().get(0);
		}
		this.notifier.fireTestFailure(new Failure(step, cause));
		step = null;
		if (Boolean.valueOf(System.getProperty(LOG_PROPERTY))) {
			System.out.println(" FAILED: " + key);
		}
	}

	@Override
	public void pending(String key) {
		super.pending(key);

		step = scenario.getChildren().get(stepCounter - 1);

		this.notifier.fireTestFailure(new Failure(step, new RuntimeException("Unknown step")));
		step = null;
		if (Boolean.valueOf(System.getProperty(LOG_PROPERTY))) {
			System.out.println(" UNKNOWN: " + key);
		}
	}

	@Override
	public void successful(String key) {
		super.successful(key);

		this.notifier.fireTestFinished(step);
		step = null;
		if (Boolean.valueOf(System.getProperty(LOG_PROPERTY))) {
			System.out.println(" SUCCESS: " + key);
		}
	}
}
