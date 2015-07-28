// generatemodel is the command invoked by go generate in order to generate the java client library.
package main

import (
	docopt "github.com/docopt/docopt-go"
	"github.com/taskcluster/taskcluster-client-java/codegenerator/model"
	"github.com/taskcluster/taskcluster-client-java/codegenerator/utils"
)

var (
	err        error
	apis       []model.APIDefinition
	schemas    map[string]*model.JsonSubSchema = make(map[string]*model.JsonSubSchema)
	schemaURLs []string

	version = "generatemodel 1.0"
	usage   = `
generatemodel
generatemodel takes input from a json file describing a set of taskcluster APIs, and generates
go source files for inclusion in the (Go) TaskCluster Client API library. It is referenced by
go generate commands in the model package. See go generate --help and ../build.sh to see how
this is used by the build process for this taskcluster-client-java go project.

  Usage:
      generatemodel -u API-MANIFEST -f SUPPLEMENTARY-DATA -o GO-OUTPUT-DIR -m MODEL-DATA-FILE
      generatemodel --help

  Options:
    -h --help               Display this help text.
    -u API-MANIFEST         The URL to retrieve the api manifest from, typically
                            http://references.taskcluster.net/manifest.json.
                            This lists the available APIs to generate, as a
                            json file containing a dictionary of api names to
                            json schema urls.
    -f SUPPLEMENTARY-DATA   Input file to read supplmentary information from
                            pertaining to the apis being generated. This
                            includes a base doc url for generating links to
                            in the generated go docs for each rest api method.
                            Typically the codegenerator/model/apis.json file.
    -o GO-OUTPUT-DIR        Directory to place generated go packages.
    -m MODEL-DATA-FILE      When all api descriptions have been downloaded and
                            parsed, and their dependencies have also been
                            processed, an overview of all the processed data
                            will be written to this file.
`
)

func main() {
	// Parse the docopt string and exit on any error or help message.
	arguments, err := docopt.Parse(usage, nil, true, version, false, true)
	utils.ExitOnFail(err)
	model.LoadAPIs(arguments["-u"].(string), arguments["-f"].(string))
	model.GenerateCode(arguments["-o"].(string), arguments["-m"].(string))
}
