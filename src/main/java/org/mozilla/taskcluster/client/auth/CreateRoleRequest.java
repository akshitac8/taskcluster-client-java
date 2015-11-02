package org.mozilla.taskcluster.client.auth;

/**
* Data to create or update a role.
*
* See http://schemas.taskcluster.net/auth/v1/create-role-request.json#
*/
public class CreateRoleRequest {

    /**
     * Description of what this role is used for in markdown.
     * Should include who is the owner, point of contact.
     */
    public String description;

    /**
     * List of scopes the role grants access to
     */
    public String[] scopes;
}