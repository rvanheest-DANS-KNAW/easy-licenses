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

import org.apache.commons.configuration.PropertiesConfiguration
import org.scalatest.{ FlatSpec, Inspectors, Matchers }

import scala.collection.JavaConverters._

class LicensesPropertiesSpec extends FlatSpec with Matchers with Inspectors {

  private val LICENSES_DIR = "src/main/assembly/dist/licenses"
  private val files = new File(LICENSES_DIR).listFiles.filter(_.isFile).map(_.getName).filterNot(n => n.endsWith("properties") || n.endsWith("json")).toList
  private val props = new PropertiesConfiguration(LICENSES_DIR + "/licenses.properties")
  private val propValues = props.getKeys.asScala.map(key => props.getString(key)).toList

  "all the files in licenses.properties" should "be present in the licenses directory" in {
    forEvery(propValues) { files should contain(_) }
  }

  "all the files in licenses directory (except .properties and .json files)" should "be present in the licenses.properties file" in {
    forEvery(files) { propValues should contain(_) }
  }
}
