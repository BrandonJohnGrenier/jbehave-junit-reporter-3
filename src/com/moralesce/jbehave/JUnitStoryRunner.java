package com.moralesce.jbehave;

import java.util.ArrayList;
import java.util.List;

import org.jbehave.core.configuration.AnnotationBuilder;
import org.jbehave.core.embedder.Embedder.RunningStoriesFailed;
import org.jbehave.core.embedder.StoryManager;
import org.jbehave.core.junit.AnnotatedPathRunner;
import org.jbehave.core.model.Story;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;

public class JUnitStoryRunner extends AnnotatedPathRunner {

	private final Description description;

	public JUnitStoryRunner(Class<?> testClass) throws Exception {
		super(testClass);
		AnnotationBuilder builder = annotationBuilder();
		StoryManager manager = builder.buildEmbedder().storyManager();

		List<Story> stories = new ArrayList<Story>();
		for (String path : builder.findPaths()) {
			stories.add(manager.storyOfPath(path));
		}

		this.description = DescriptionGenerator.createDescription(stories, testClass);
	}

	@Override
	public Description getDescription() {
		return this.description;
	}

	@Override
	public void run(RunNotifier notifier) {

		JUnitStoryReporter.getInstance().setNotifier(notifier);
		JUnitStoryReporter.getInstance().setDescription(description);

		try {

			annotationBuilder().buildEmbedder().runStoriesAsPaths(annotationBuilder().findPaths());

		} catch (RunningStoriesFailed rsf) {
			rsf.printStackTrace();
			System.out.println(getClass().getSimpleName() + ": " + rsf.getClass().getSimpleName() + " ignored");
		}
	}
}