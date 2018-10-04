package org.jclouds.googlecomputeengine.compute.domain.internal;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.net.URI;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.jclouds.googlecloud.config.GoogleCloudProperties.PROJECT_NAME;

public class RegionAndNameInProject {

    private String regionId;
    private String name;
    private String currentProject;

    @Inject
    RegionAndNameInProject(@Named(PROJECT_NAME) String currentProject, String regionId, String name) {
        this.regionId = regionId;
        this.currentProject = currentProject;
        this.name = name;
    }

    public URI getURI() {
        return URI.create(String.format("projects/%s/regions/%s/subnetworks%s", currentProject, regionId, name));
    }

    public static RegionAndNameInProject fromURI(String uri) {
        Iterable<String> parts = Splitter.on("/").split(checkNotNull(uri, "uri"));
        checkArgument(Iterables.size(parts) == 6, "uri must be in format projects/project/regions/region/subnetworks/subnetwork");
        return fromRegionAndNameInProject(
                Iterables.get(parts, 2),
                Iterables.get(parts, 4),
                Iterables.get(parts, 6)
        );
    }

    public static RegionAndNameInProject fromRegionAndNameInProject(String project, String regionId, String name) {
            return new RegionAndNameInProject(project, regionId, name);
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentProject() {
        return currentProject;
    }

    public void setCurrentProject(String currentProject) {
        this.currentProject = currentProject;
    }
}
