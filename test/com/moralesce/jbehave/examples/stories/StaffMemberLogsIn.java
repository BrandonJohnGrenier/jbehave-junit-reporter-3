package com.moralesce.jbehave.examples.stories;

import org.jbehave.core.annotations.Configure;
import org.jbehave.core.annotations.UsingEmbedder;
import org.jbehave.core.annotations.UsingPaths;
import org.jbehave.core.annotations.UsingSteps;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.junit.runner.RunWith;

import com.moralesce.jbehave.JUnitStoryReporter;
import com.moralesce.jbehave.JUnitStoryRunner;
import com.moralesce.jbehave.examples.steps.LoginSteps;
import com.moralesce.jbehave.examples.stories.StaffMemberLogsIn.MyReportBuilder;
import com.moralesce.jbehave.examples.stories.StaffMemberLogsIn.MyStoryLoader;

@RunWith(JUnitStoryRunner.class)
@UsingEmbedder(embedder = Embedder.class)
@UsingPaths(searchIn = "test", includes = { "**/*.story" })

@Configure(storyLoader = MyStoryLoader.class, storyReporterBuilder = MyReportBuilder.class)
@UsingSteps(instances = { LoginSteps.class })
public class StaffMemberLogsIn {

	public static class MyStoryLoader extends LoadFromClasspath {
		public MyStoryLoader() {
			super(StaffMemberLogsIn.class.getClassLoader());
		}
	}

	public static class MyReportBuilder extends StoryReporterBuilder {
		public MyReportBuilder() {
			this.withDefaultFormats().withReporters(JUnitStoryReporter.getInstance());
		}
	}

}
