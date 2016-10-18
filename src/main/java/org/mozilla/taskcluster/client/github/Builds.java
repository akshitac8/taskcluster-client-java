package org.mozilla.taskcluster.client.github;

import java.util.Date;

/**
 * A paginated list of builds
 *
 * See http://schemas.taskcluster.net/github/v1/build-list.json#
 */
public class Builds {

    public class BuildsEntry {

        /**
         * The initial creation time of the build. This is when it became pending.
         *
         * See http://schemas.taskcluster.net/github/v1/build-list.json#/properties/builds/items/properties/created
         */
        public Date created;

        /**
         * Github organization associated with the build.
         *
         * Syntax:     ^([a-zA-Z0-9-_%]*)$
         * Min length: 1
         * Max length: 100
         *
         * See http://schemas.taskcluster.net/github/v1/build-list.json#/properties/builds/items/properties/organization
         */
        public String organization;

        /**
         * Github repository associated with the build.
         *
         * Syntax:     ^([a-zA-Z0-9-_%]*)$
         * Min length: 1
         * Max length: 100
         *
         * See http://schemas.taskcluster.net/github/v1/build-list.json#/properties/builds/items/properties/repository
         */
        public String repository;

        /**
         * Github revision associated with the build.
         *
         * Min length: 40
         * Max length: 40
         *
         * See http://schemas.taskcluster.net/github/v1/build-list.json#/properties/builds/items/properties/sha
         */
        public String sha;

        /**
         * Github status associated with the build.
         *
         * Possible values:
         *     * "pending"
         *     * "success"
         *     * "error"
         *     * "failure"
         *
         * See http://schemas.taskcluster.net/github/v1/build-list.json#/properties/builds/items/properties/state
         */
        public String state;

        /**
         * Taskcluster task-group associated with the build.
         *
         * Syntax:     ^[A-Za-z0-9_-]{8}[Q-T][A-Za-z0-9_-][CGKOSWaeimquy26-][A-Za-z0-9_-]{10}[AQgw]$
         *
         * See http://schemas.taskcluster.net/github/v1/build-list.json#/properties/builds/items/properties/taskGroupId
         */
        public String taskGroupId;

        /**
         * The last updated of the build. If it is done, this is when it finished.
         *
         * See http://schemas.taskcluster.net/github/v1/build-list.json#/properties/builds/items/properties/updated
         */
        public Date updated;
    }

    /**
     * A simple list of builds.
     *
     * See http://schemas.taskcluster.net/github/v1/build-list.json#/properties/builds
     */
    public BuildsEntry[] builds;

    /**
     * Passed back from Azure to allow us to page through long result sets.
     *
     * See http://schemas.taskcluster.net/github/v1/build-list.json#/properties/continuationToken
     */
    public String continuationToken;
}