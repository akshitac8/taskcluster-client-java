package org.mozilla.taskcluster.client.github;

/**
 * Create a commit status on GitHub.
 * Full specification on [GitHub docs](https://developer.github.com/v3/repos/statuses/#create-a-status)
 *
 * See http://schemas.taskcluster.net/github/v1/create-status.json#
 */
public class CreateStatus {

    /**
     * A string label to differentiate this status from the status of other systems.
     *
     * See http://schemas.taskcluster.net/github/v1/create-status.json#/properties/context
     */
    public String context;

    /**
     * A short description of the status.
     *
     * See http://schemas.taskcluster.net/github/v1/create-status.json#/properties/description
     */
    public String description;

    /**
     * The state of the status.
     *
     * Possible values:
     *     * "pending"
     *     * "success"
     *     * "error"
     *     * "failure"
     *
     * See http://schemas.taskcluster.net/github/v1/create-status.json#/properties/state
     */
    public String state;

    /**
     * The target URL to associate with this status. This URL will be linked from the GitHub UI to allow users to easily see the 'source' of the Status.
     *
     * See http://schemas.taskcluster.net/github/v1/create-status.json#/properties/target_url
     */
    public String target_url;
}
