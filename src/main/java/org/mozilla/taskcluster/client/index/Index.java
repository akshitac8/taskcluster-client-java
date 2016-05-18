// The following code is AUTO-GENERATED. Please DO NOT edit.
//
// This package was generated from the schema defined at
// http://references.taskcluster.net/index/v1/api.json
package org.mozilla.taskcluster.client.index;

import org.mozilla.taskcluster.client.APICallFailure;
import org.mozilla.taskcluster.client.CallSummary;
import org.mozilla.taskcluster.client.Credentials;
import org.mozilla.taskcluster.client.EmptyPayload;
import org.mozilla.taskcluster.client.TaskClusterRequestHandler;

/**
 * The task index, typically available at `index.taskcluster.net`, is
 * responsible for indexing tasks. In order to ensure that tasks can be
 * located by recency and/or arbitrary strings. Common use-cases includes
 * 
 *  * Locate tasks by git or mercurial `<revision>`, or
 *  * Locate latest task from given `<branch>`, such as a release.
 * 
 * **Index hierarchy**, tasks are indexed in a dot `.` separated hierarchy
 * called a namespace. For example a task could be indexed in
 * `<revision>.linux-64.release-build`. In this case the following
 * namespaces is created.
 * 
 *  1. `<revision>`, and,
 *  2. `<revision>.linux-64`
 * 
 * The inside the namespace `<revision>` you can find the namespace
 * `<revision>.linux-64` inside which you can find the indexed task
 * `<revision>.linux-64.release-build`. In this example you'll be able to
 * find build for a given revision.
 * 
 * **Task Rank**, when a task is indexed, it is assigned a `rank` (defaults
 * to `0`). If another task is already indexed in the same namespace with
 * the same lower or equal `rank`, the task will be overwritten. For example
 * consider a task indexed as `mozilla-central.linux-64.release-build`, in
 * this case on might choose to use a unix timestamp or mercurial revision
 * number as `rank`. This way the latest completed linux 64 bit release
 * build is always available at `mozilla-central.linux-64.release-build`.
 * 
 * **Indexed Data**, when a task is located in the index you will get the
 * `taskId` and an additional user-defined JSON blob that was indexed with
 * task. You can use this to store additional information you would like to
 * get additional from the index.
 * 
 * **Entry Expiration**, all indexed entries must have an expiration date.
 * Typically this defaults to one year, if not specified. If you are
 * indexing tasks to make it easy to find artifacts, consider using the
 * expiration date that the artifacts is assigned.
 * 
 * **Valid Characters**, all keys in a namespace `<key1>.<key2>` must be
 * in the form `/[a-zA-Z0-9_!~*'()%-]+/`. Observe that this is URL-safe and
 * that if you strictly want to put another character you can URL encode it.
 * 
 * **Indexing Routes**, tasks can be indexed using the API below, but the
 * most common way to index tasks is adding a custom route on the following
 * form `index.<namespace>`. In-order to add this route to a task you'll
 * need the following scope `queue:route:index.<namespace>`. When a task has
 * this route, it'll be indexed when the task is **completed successfully**.
 * The task will be indexed with `rank`, `data` and `expires` as specified
 * in `task.extra.index`, see example below:
 * 
 * ```js
 * {
 *   payload:  { /* ... * / },
 *   routes: [
 *     // index.<namespace> prefixed routes, tasks CC'ed such a route will
 *     // be indexed under the given <namespace>
 *     "index.mozilla-central.linux-64.release-build",
 *     "index.<revision>.linux-64.release-build"
 *   ],
 *   extra: {
 *     // Optional details for indexing service
 *     index: {
 *       // Ordering, this taskId will overwrite any thing that has
 *       // rank <= 4000 (defaults to zero)
 *       rank:       4000,
 * 
 *       // Specify when the entries expires (Defaults to 1 year)
 *       expires:          new Date().toJSON(),
 * 
 *       // A little informal data to store along with taskId
 *       // (less 16 kb when encoded as JSON)
 *       data: {
 *         hgRevision:   "...",
 *         commitMessae: "...",
 *         whatever...
 *       }
 *     },
 *     // Extra properties for other services...
 *   }
 *   // Other task properties...
 * }
 * ```
 * 
 * **Remark**, when indexing tasks using custom routes, it's also possible
 * to listen for messages about these tasks. Which is quite convenient, for
 * example one could bind to `route.index.mozilla-central.*.release-build`,
 * and pick up all messages about release builds. Hence, it is a
 * good idea to document task index hierarchies, as these make up extension
 * points in their own.
 *
 * See: https://docs.taskcluster.net/reference/core/index/api-docs
 */
public class Index extends TaskClusterRequestHandler {

    protected static final String defaultBaseURL = "https://index.taskcluster.net/v1";

    public Index(Credentials credentials) {
        super(credentials, defaultBaseURL);
    }

    public Index(Credentials credentials, String baseURL) {
        super(credentials, baseURL);
    }

    public Index(String clientId, String accessToken) {
        super(new Credentials(clientId, accessToken), defaultBaseURL);
    }

    public Index(String clientId, String accessToken, String certificate) {
        super(new Credentials(clientId, accessToken, certificate), defaultBaseURL);
    }

    public Index(String baseURL) {
        super(baseURL);
    }

    public Index() {
        super(defaultBaseURL);
    }

    /**
     * Find task by namespace, if no task existing for the given namespace, this
     * API end-point respond `404`.
     *
     * See https://docs.taskcluster.net/reference/core/index/api-docs/#findTask
     */
    public CallSummary<EmptyPayload, IndexedTaskResponse> findTask(String namespace) throws APICallFailure {
        return apiCall(null, "GET", "/task/" + uriEncode(namespace), IndexedTaskResponse.class);
    }

    /**
     * List the namespaces immediately under a given namespace. This end-point
     * list up to 1000 namespaces. If more namespaces are present a
     * `continuationToken` will be returned, which can be given in the next
     * request. For the initial request, the payload should be an empty JSON
     * object.
     * 
     * **Remark**, this end-point is designed for humans browsing for tasks, not
     * services, as that makes little sense.
     *
     * See https://docs.taskcluster.net/reference/core/index/api-docs/#listNamespaces
     */
    public CallSummary<ListNamespacesRequest, ListNamespacesResponse> listNamespaces(String namespace, ListNamespacesRequest payload) throws APICallFailure {
        return apiCall(payload, "POST", "/namespaces/" + uriEncode(namespace), ListNamespacesResponse.class);
    }

    /**
     * List the tasks immediately under a given namespace. This end-point
     * list up to 1000 tasks. If more tasks are present a
     * `continuationToken` will be returned, which can be given in the next
     * request. For the initial request, the payload should be an empty JSON
     * object.
     * 
     * **Remark**, this end-point is designed for humans browsing for tasks, not
     * services, as that makes little sense.
     *
     * See https://docs.taskcluster.net/reference/core/index/api-docs/#listTasks
     */
    public CallSummary<ListTasksRequest, ListTasksResponse> listTasks(String namespace, ListTasksRequest payload) throws APICallFailure {
        return apiCall(payload, "POST", "/tasks/" + uriEncode(namespace), ListTasksResponse.class);
    }

    /**
     * Insert a task into the index. Please see the introduction above, for how
     * to index successfully completed tasks automatically, using custom routes.
     *
     * See https://docs.taskcluster.net/reference/core/index/api-docs/#insertTask
     */
    public CallSummary<InsertTaskRequest, IndexedTaskResponse> insertTask(String namespace, InsertTaskRequest payload) throws APICallFailure {
        return apiCall(payload, "PUT", "/task/" + uriEncode(namespace), IndexedTaskResponse.class);
    }

    /**
     * Find task by namespace and redirect to artifact with given `name`,
     * if no task existing for the given namespace, this API end-point respond
     * `404`.
     *
     * See https://docs.taskcluster.net/reference/core/index/api-docs/#findArtifactFromTask
     */
    public CallSummary<EmptyPayload, EmptyPayload> findArtifactFromTask(String namespace, String name) throws APICallFailure {
        return apiCall(null, "GET", "/task/" + uriEncode(namespace) + "/artifacts/" + uriEncode(name), EmptyPayload.class);
    }

    /**
     * Documented later...
     * 
     * **Warning** this api end-point is **not stable**.
     *
     * See https://docs.taskcluster.net/reference/core/index/api-docs/#ping
     */
    public CallSummary<EmptyPayload, EmptyPayload> ping() throws APICallFailure {
        return apiCall(null, "GET", "/ping", EmptyPayload.class);
    }
}