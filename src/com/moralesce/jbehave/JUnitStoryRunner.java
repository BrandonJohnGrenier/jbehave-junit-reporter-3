package com.moralesce.jbehave;

import org.jbehave.core.configuration.AnnotationBuilder;
import org.jbehave.core.embedder.StoryManager;
import org.jbehave.core.junit.AnnotatedPathRunner;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;

public class JUnitStoryRunner extends AnnotatedPathRunner {

	private static int storyCounter = 1;
	private final Description description;

	public JUnitStoryRunner(Class<?> testClass) throws Exception {
		super(testClass);
		AnnotationBuilder builder = new AnnotationBuilder(testClass);
		StoryManager manager = builder.buildEmbedder().storyManager();
		this.description = DescriptionGenerator.createDescription(manager.storyOfPath(builder.findPaths().get(0)), testClass);
		storyCounter += 1;
	}

	public Description getDescription() {
		return this.description;
	}

	public void run(RunNotifier notifier) {
		JUnitStoryReporter.withRunNotifier(notifier);
		JUnitStoryReporter.withDescription(description);
		super.run(notifier);
	}

	public static int getStoryCounter() {
		return storyCounter;
	}

}
