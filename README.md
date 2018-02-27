# Gaia-Clim

The aim of the Gap Analysis for Integrated Atmospheric ECV CLImate Monitoring (GAIA-CLIM) project is to
improve our ability to use ground-based and sub-orbital observations to characterise satellite observations for a
number of atmospheric Essential Climate Variables (ECVs). The key outcomes will be a “Virtual Observatory”
(VO) facility of co-locations and their uncertainties and a report on gaps in capabilities or understanding,
which shall be used to inform subsequent Horizon 2020 activities. In particular, Work Package 1 (WP1) of the
GAIA-CLIM project is devoted to the geographical mapping of existing non-satellite measurement capabilities
for a number of ECVs in the atmospheric, oceanic and terrestrial domains.
The work carried out within WP1 has allowed to provide the users with an up-to-date geographical identification, at
the European and global scales, of current surface-based, balloon-based and oceanic (floats) observing capabilities
on an ECV by ECV basis for several parameters which can be obtained using space-based observations from past,
present and planned satellite missions.
Having alighted on a set of metadata schema to follow, a consistent collection of discovery metadata has been
provided into a common structure and will be made available to users through the GAIA-CLIM VO in 2018.
Metadata can be interactively visualized through a 3D Graphical User Interface. The metadataset includes 54
plausible networks and 2 aircraft permanent infrastructures for EO Characterisation in the context of GAIA-CLIM
currently operating on different spatial domains and measuring different ECVs using one or more measurement
techniques. Each classified network has in addition been assessed for suitability against metrological criteria to
identifyy those with a level of maturity which enables closure on a comparison with satellite measurements.
The metadata GUI is based on Cesium, a virtual globe freeware and open source written in Javascript. It allows
users to apply different filters to the data displayed on the globe, selecting data per ECV, network, measurements
type and level of maturity. Filtering is operated with a query to GeoServer web application through the WFS
interface on a data layer configured on our DB Postgres with PostGIS extension; filters set on the GUI are
expressed using ECQL (Extended Common Query Language).
The GUI allows to visualize in real-time the current non-satellite observing capabilities along with the satellite
platforms measuring the same ECVs. Satellite ground track and footprint of the instruments on board can be also
visualized.
This work contributes to improve metadata and web map services and to facilitate users’ experience in the
spatio-temporal analysis of Earth Observation data.


Gaia-Clim website: http://www.gaia-clim.eu/

Virtual Observatory: http://193.40.13.83/vo/index.html
