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

public abstract class AbstractStoryReporter {

	public void beforeStory(Story story, boolean arg1) {

	}

	public void afterExamples() {

	}

	public void afterScenario() {

	}

	public void afterStory(boolean arg0) {

	}

	public void beforeExamples(List<String> arg0, ExamplesTable arg1) {

	}

	public void beforeScenario(String arg0) {

	}

	public void beforeStep(String arg0) {

	}

	public void dryRun() {

	}

	public void example(Map<String, String> arg0) {

	}

	public void failedOutcomes(String arg0, OutcomesTable arg1) {

	}

	public void givenStories(GivenStories arg0) {

	}

	public void givenStories(List<String> arg0) {

	}

	public void ignorable(String arg0) {

	}

	public void narrative(Narrative arg0) {

	}

	public void notPerformed(String arg0) {

	}

	public void pending(String key) {

	}

	public void pendingMethods(List<String> arg0) {

	}

	public void restarted(String arg0, Throwable arg1) {

	}

	public void scenarioMeta(Meta arg0) {

	}

	public void scenarioNotAllowed(Scenario arg0, String arg1) {

	}

	public void storyCancelled(Story arg0, StoryDuration arg1) {

	}

	public void storyNotAllowed(Story arg0, String arg1) {

	}

}
