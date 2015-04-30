/*
 * Copyright 1998-2014 University Corporation for Atmospheric Research/Unidata
 *
 *   Portions of this software were developed by the Unidata Program at the
 *   University Corporation for Atmospheric Research.
 *
 *   Access and use of this software shall impose the following obligations
 *   and understandings on the user. The user is granted the right, without
 *   any fee or cost, to use, copy, modify, alter, enhance and distribute
 *   this software, and any derivative works thereof, and its supporting
 *   documentation for any purpose whatsoever, provided that this entire
 *   notice appears in all copies of the software, derivative works and
 *   supporting documentation.  Further, UCAR requests that the user credit
 *   UCAR/Unidata in any publications that result from the use of this
 *   software or in any product that includes this software. The names UCAR
 *   and/or Unidata, however, may not be used in any advertising or publicity
 *   to endorse or promote any products or commercial entity unless specific
 *   written permission is obtained from UCAR/Unidata. The user also
 *   understands that UCAR/Unidata is not obligated to provide the user with
 *   any support, consulting, training or assistance of any kind with regard
 *   to the use, operation and performance of this software nor to provide
 *   the user with any updates, revisions, new versions or "bug fixes."
 *
 *   THIS SOFTWARE IS PROVIDED BY UCAR/UNIDATA "AS IS" AND ANY EXPRESS OR
 *   IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *   WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *   DISCLAIMED. IN NO EVENT SHALL UCAR/UNIDATA BE LIABLE FOR ANY SPECIAL,
 *   INDIRECT OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING
 *   FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT,
 *   NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION
 *   WITH THE ACCESS, USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package thredds.server.ncss.format;

import thredds.util.ContentType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An enumeration of the possible ncss download formats
 */

public enum SupportedFormat {

  CSV_STREAM("csv", true, false, ContentType.csv.toString(), "text/csv"),
  CSV_FILE("csv_file", false, false, ContentType.csv.toString(), "csv_file"),

  XML_STREAM("xml", true, false, ContentType.xml.toString(), "xml"),
  XML_FILE("xml_file", false, false, ContentType.xml.toString(), "xml_file"),

  NETCDF3("netcdf3", false, true, ContentType.netcdf.toString(), "netcdf", "netcdf3"),
  NETCDF4("netcdf4-classic", false, true, ContentType.netcdf.toString(), "netcdf4-classic"),
  NETCDF4EXT("netcdf4", false, true, ContentType.netcdf.toString(), "netcdf4"),

  JSON("json", false, false, ContentType.json.toString(), "json", "geojson"),
  WKT("wkt", false, false, ContentType.text.toString(), "wkt"),

  WATERML2("waterml2", true, false, ContentType.xml.toString(), "waterml2");

  /*
   * First alias is used as content-type in the http headers
   */
  private final List<String> aliases;
  private final String formatName;
  private final String mimeType;
  private final boolean isStream;
  private final boolean isBinary;

  SupportedFormat(String formatName, boolean isStream, boolean isBinary, String mimeType, String... aliases) {
    this.formatName = formatName;
    this.isStream = isStream;
    this.isBinary = isBinary;
    this.mimeType = mimeType;
    List<String> aliasesList = new ArrayList<>();
    Collections.addAll(aliasesList, aliases);
    this.aliases = Collections.unmodifiableList(aliasesList);
  }

  public String getFormatName() {
    return formatName;
  }

  public String getMimeType() {
    return mimeType;
  }

  public List<String> getAliases() {
    return aliases;
  }

  public boolean isAlias(String want) {
    if (want.equalsIgnoreCase(formatName)) return true;
    if (want.equalsIgnoreCase(mimeType)) return true;
    for (String have : aliases)
      if (have.equalsIgnoreCase(want)) return true;
    return false;
  }

  public boolean isStream() {
    return isStream;
  }

  public boolean isBinary() {
    return isBinary;
  }

  public boolean isText() {
    return !isBinary;
  }
}
