package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
 * Response to a successful task claim
 *
 * See http://schemas.taskcluster.net/queue/v1/task-claim-response.json#
 */
public class TaskClaimResponse {

    public class Credentials {

        /**
         * The `accessToken` for the temporary credentials.
         *
         * Min length: 1
         *
         * See http://schemas.taskcluster.net/queue/v1/task-claim-response.json#/properties/credentials/properties/accessToken
         */
        public String accessToken;

        /**
         * The `certificate` for the temporary credentials, these are required
         * for the temporary credentials to work.
         *
         * Min length: 1
         *
         * See http://schemas.taskcluster.net/queue/v1/task-claim-response.json#/properties/credentials/properties/certificate
         */
        public String certificate;

        /**
         * The `clientId` for the temporary credentials.
         *
         * Min length: 1
         *
         * See http://schemas.taskcluster.net/queue/v1/task-claim-response.json#/properties/credentials/properties/clientId
         */
        public String clientId;
    }

    /**
     * Temporary credentials granting `task.scopes` and the scope:
     * `queue:claim-task:<taskId>/<runId>` which allows the worker to reclaim
     * the task, upload artifacts and report task resolution.
     *
     * The temporary credentials are set to expire after `takenUntil`. They
     * won't expire exactly at `takenUntil` but shortly after, hence, requests
     * coming close `takenUntil` won't have problems even if there is a little
     * clock drift.
     *
     * Workers should use these credentials when making requests on behalf of
     * a task. This includes requests to create artifacts, reclaiming the task
     * reporting the task `completed`, `failed` or `exception`.
     *
     * Note, a new set of temporary credentials is issued when the worker
     * reclaims the task.
     *
     * See http://schemas.taskcluster.net/queue/v1/task-claim-response.json#/properties/credentials
     */
    public Credentials credentials;

    /**
     * `run-id` assigned to this run of the task
     *
     * Mininum:    0
     * Maximum:    1000
     *
     * See http://schemas.taskcluster.net/queue/v1/task-claim-response.json#/properties/runId
     */
    public int runId;

    /**
     * See http://schemas.taskcluster.net/queue/v1/task-claim-response.json#/properties/status
     */
    public TaskStatusStructure status;

    /**
     * Time at which the run expires and is resolved as `exception`,
     * with reason `claim-expired` if the run haven't been reclaimed.
     *
     * See http://schemas.taskcluster.net/queue/v1/task-claim-response.json#/properties/takenUntil
     */
    public Date takenUntil;

    /**
     * See http://schemas.taskcluster.net/queue/v1/task-claim-response.json#/properties/task
     */
    public TaskDefinitionResponse task;

    /**
     * Identifier for the worker-group within which this run started.
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See http://schemas.taskcluster.net/queue/v1/task-claim-response.json#/properties/workerGroup
     */
    public String workerGroup;

    /**
     * Identifier for the worker executing this run.
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See http://schemas.taskcluster.net/queue/v1/task-claim-response.json#/properties/workerId
     */
    public String workerId;
}
