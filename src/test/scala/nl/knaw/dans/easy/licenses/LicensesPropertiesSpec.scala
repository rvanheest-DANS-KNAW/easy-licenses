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

import scala.collection.JavaConverters._

class LicensesPropertiesSpec extends PropsFixture {

  private val propFiles: Seq[String] = props.getKeys.asScala
    .map(key => props.getString(key))
    .toSeq
  val propBaseFileNames: Seq[String] = propFiles
    .map(stripExtension)
    .sortBy(identity).distinct

  "all the files in licenses.properties" should "be present in the licenses directory and vice versa" in {
    propBaseFileNames shouldBe documentBaseFileNames
  }

  "a file with extension specified in licenses.properties" should "exist in the licenses directory" in {
    forEvery(propFiles) { fileName => file(fileName) should exist }
  }

  "all license files" should "have a text version" in { // used by easy-deposit-agreement-creator
    forEvery(propBaseFileNames) { baseFileName => file(s"$baseFileName.txt") should exist }
  }

  private def file(fileName: String) = {
    new File(LICENSES_DIR, fileName)
  }
}
