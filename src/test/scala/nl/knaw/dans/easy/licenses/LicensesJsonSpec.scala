/**
 * Copyright (C) 2019 DANS - Data Archiving and Networked Services (info@dans.knaw.nl)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.knaw.dans.easy.licenses

import java.io.File

import org.json4s._
import org.json4s.native.JsonMethods._
import org.scalatest.{ FlatSpec, Inspectors, Matchers }

class LicensesJsonSpec extends FlatSpec with Matchers with Inspectors {

  private val LICENSES_DIR = "src/main/assembly/dist/licenses"
  private val files = new File(LICENSES_DIR).listFiles.filter(_.isFile).map(_.getName).filterNot(n => n.endsWith("properties") || n.endsWith("json")).toList
  private val fileNames = files.map(f => f.substring(0, f.lastIndexOf(".")))
  private val json = parse(new File(LICENSES_DIR, "licenses.json"))
  private val viewNames = for {
    JObject(file) <- json
    JField("title", viewName) <- file
  } yield viewName.values

  "all the files in licenses.json" should "be present in the licenses directory" in {
    forEvery(viewNames) { fileNames should contain(_) }
  }

  "all the files in licenses directory (except .properties and .json files)" should "be present in the licenses.json file" in {
    fileNames.foreach(viewNames should contain(_))
    forEvery(fileNames) { viewNames should contain(_) }
  }
}
