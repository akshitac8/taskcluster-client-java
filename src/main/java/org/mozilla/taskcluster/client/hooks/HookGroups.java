package org.mozilla.taskcluster.client.hooks;

/**
 * List of `hookGroupIds`.
 *
 * See http://schemas.taskcluster.net/hooks/v1/list-hook-groups-response.json#
 */
public class HookGroups {

    /**
     * See http://schemas.taskcluster.net/hooks/v1/list-hook-groups-response.json#/properties/groups
     */
    public String[] groups;
}
