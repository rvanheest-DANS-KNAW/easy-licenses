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

import org.scalatest.{ FlatSpec, Inspectors, Matchers }

trait TestFixture extends FlatSpec with Matchers with Inspectors {

  val LICENSES_DIR = "src/main/resources/licenses"

  val documentFiles: List[String] = new File(LICENSES_DIR)
    .listFiles.filter(_.isFile).map(_.getName)
    .filterNot(n => n.endsWith("properties") || n.endsWith("json"))
    .toList

  val documentBaseFileNames: List[String] = documentFiles
    .map(stripExtension)
    .sortBy(identity).distinct

  def stripExtension(fileName: String): String = {
    fileName.replaceAll(".[^.]+$", "")
  }
}
