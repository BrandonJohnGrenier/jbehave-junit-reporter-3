package com.moralesce.jbehave;

import java.util.List;
import java.util.Map;

import org.jbehave.core.model.ExamplesTable;
import org.jbehave.core.model.GivenStories;
import org.jbehave.core.model.Meta;
import org.jbehave.core.model.Narrative;
import org.jbehave.core.model.OutcomesTable;
import org.jbehave.core.model.Scenario;
import org.jbehave.core.model.Story;
import org.jbehave.core.model.StoryDuration;
import org.jbehave.core.reporters.StoryReporter;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;

public abstract class AbstractStoryReporter implements StoryReporter {

	protected RunNotifier notifier;
	protected Description suiteDescription;

	protected int storyCounter;
	protected int scenarioCounter;
	protected int stepCounter;

	protected void setNotifier(RunNotifier notifier) {
		this.notifier = notifier;
	}

	protected void setDescription(Description description) {
		this.suiteDescription = description;
	}

	@Override
	public void beforeStory(Story story, boolean givenStory) {

		if (story.getName().equals("BeforeStories") || story.getName().equals("AfterStories")) {
			storyCounter = 0;
			return;
		}

		storyCounter++;
		scenarioCounter = 0;
	}

	@Override
	public void afterExamples() {

	}

	@Override
	public void afterScenario() {

	}

	@Override
	public void afterStory(boolean givenStory) {

	}

	@Override
	public void beforeExamples(List<String> steps, ExamplesTable table) {

	}

	@Override
	public void beforeScenario(String scenarioTitle) {

		scenarioCounter++;
		stepCounter = 0;
	}

	@Override
	public void beforeStep(String step) {
		stepCounter++;
	}

	@Override
	public void dryRun() {

	}

	@Override
	public void example(Map<String, String> tableRow) {
	}

	@Override
	public void failedOutcomes(String step, OutcomesTable table) {

	}

	@Override
	public void givenStories(GivenStories stories) {

	}

	@Override
	public void givenStories(List<String> stories) {

	}

	@Override
	public void ignorable(String step) {
	}

	@Override
	public void narrative(Narrative narrative) {
	}

	@Override
	public void notPerformed(String step) {
	}

	@Override
	public void failed(String step, Throwable cause) {

	}

	@Override
	public void pending(String step) {
		stepCounter++;
	}

	@Override
	public void successful(String step) {

	}

	@Override
	public void pendingMethods(List<String> pending) {

	}

	@Override
	public void restarted(String step, Throwable cause) {
	}

	@Override
	public void scenarioMeta(Meta meta) {

	}

	@Override
	public void scenarioNotAllowed(Scenario scenario, String filter) {

	}

	@Override
	public void storyCancelled(Story story, StoryDuration duration) {

	}

	@Override
	public void storyNotAllowed(Story story, String filter) {

	}
}
