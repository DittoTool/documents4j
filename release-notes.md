documents4j release notes
-------------------------

### 22. April 2021: Version 1.1.6

- Add support for MS PowerPoint.

### 14. September 2020: Version 1.1.5

- Minor conversion script fixes.

### 10. August 2020: Version 1.1.4

- Remove pseudo-password from scripts since it causes breakage with several versions of MS Word and Excel.
- Clear Word error on start if restarted.

### 28. Mars 2020: Version 1.1.3

- Adjust script for Excel conversion to provide the pseudo-password as the right argument.
- Introduce possibility to override scripts by setting `com.documents4j.conversion.msoffice`-prefixed system properties.

### 28. Mars 202: Version 1.1.2

- Avoid freeze if Word or Excel document is password protected.

### 4. September 2019: Version 1.1.1

- Fixes an invalid input validation.

### 2. September 2019: Version 1.1.0

- Add monitoring endpoints.
- Make Java 11 compatible and increase to Java 11.
- Update dependencies.
- Allow for SSL and basic auth.
- Allow running standalone server without using system in.

### 23. December 2016: Version 1.0.3

Fixed escaping when using the converter in folders with spaces and fixed a command line bug caused by SSL inclusion.

### 29. January 2016: Version 1.0.2

Added additional formats.

### 22. January 2016: Version 1.0.1

Added additional formats.

### 27. November 2015: Version 1.0.0

Added an aggregating converter API and SSL support for remote conversion.

### 4. February 2015: Version 0.2.1

Fixed a racing condition where a future is released as done before its registered callbacks are executed.

### 14. July 2014: Version 0.2

First general release. All former releases only contained a MS Word-to-PDF converter which lived in a different
name space.
