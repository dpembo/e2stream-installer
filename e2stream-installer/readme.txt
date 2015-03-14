+----------------------------------------------------------+
|   ______ ___   _____ _                                   |
|  |  ____|__ \ / ____| |                                  |
|  | |__     ) | (___ | |_ _ __ ___  __ _ _ __ ___         |
|  |  __|   / / \___ \| __| '__/ _ \/ _` | '_ ` _ \        |
|  | |____ / /_ ____) | |_| | |  __/ (_| | | | | | |       |
|  |______|____|_____/ \__|_|  \___|\__,_|_| |_| |_|       |
|                                                          |
|                         Copyright (c) 2015 - pembo.co.uk |
+----------------------------------------------------------+                              
|                          http://www.pembo.co.uk/e2stream |
+----------------------------------------------------------+

Change log
v6.1 - 15/02/2015
* Resolved channel up/down button issue when streaming a
  channel
  
v6.0 - 07/02/2015
* Up/Down arrows buttons whilst whilst watching a channel 
  now allow you to browse now/next for the channels in the 
  currently active bouquet
* Whilst browsing now/next 'enter' will select and tune the
  current channel.
* Fixed info-bar display on pause
* Up/Down arrow buttons also display the info bar now

v5.7 - 30/01/2015
* Fixed issue with empty recordings list causing errors
* URL Encoded service id for those people who have unusual
  service id values

v5.6 - 26/01/2015
* Resolved issue with return button and infobar cancel
* Resolved issue with now/next detail showing when constant
  buffering occurs
* Moved the 'buffering' text just slightly to make it more
  visible
* Removed redundant code for infobar in preparation for
  coming changes
* Extended debug view to fill the pane with lines
* Reduced font-size in NOW/NEXT detail and stopped text from
  overflowing the container
  
v5.5 - 19/01/2015
* Updated return button to also cancel infobar when active
* Added last channel/bouquet cache to store last active  
  channel/bouquet to enable further functionality
* Added '### Last Active Bouquet' as an option to the startup
  bouquet choice so that the app can startup in the bouquet
  you last browsed from the previous run of E2Stream.
    
v5.4 - 17/01/2015
* Added now/next description when in full screen mode.  To
  show press the 'info' button when the info bar is showing.
  The info bar has a 6 second timeout and will hide after 
  this timeout unless you show now/next detail which cancels
  the timeout.
* Moved alert bar, audio bar and changed z-index
* Changed z-index on volume/mute OSD.
* Altered debug view to highlight the more important log
  messages ('tools' button).
* Separated CSS into separate files for ease of change
  
v5.3 - 09/01/2015
* Moved transcode to settings screen
* Added 'multi-transcoder' settings to the advanced settings 
  screen  for those receivers that use this.  Note:
  - On enabling multi-transcode it disables the default 
    transcode mechanism as it's not relevant
  - Doesn't apply to recordings as this is a different e2 api
* Fixed(hopefully?) return button for H-Series TVs to enable
  the exit dialog, and return to exit from full screen player.
* Fixed advanced/parental settings to return to the settings
  screen rather than back to the main screen
* Made Rewind button delete a character on number entry in
  settings/advanced settings screen
* Following resolution of enter issue in v5.2, changed the help
  bar to reference the enter button now.
* Improved error handling on some network/stream communications
  
v5.2 - 05/01/2015
* Fixed 'Enter' button on H-Series TVs

v5.1 - 04/01/2015
* Fixed Audio Class issues with C/D Series preventing input of
  IP on startup.

v5.0 - 04/01/2015
* Added system sounds to the various functions within the app
* Return now exits from full screen back to the channel list
* Implemented a dialog control to handle new functionality
* Added confirm dialog when using return from channel list
  to exit.  This allows you to make the choice to: 
  - A: Cancel the exit request
  - B: Exit E2Stream App
  - C: Suspend the E2 box and Exit E2Stream
  - D: Deep Suspend the E2 box and Exit E2Stream
  Obviously, if you deep suspend the E2 box, you won't be able
  to use the app again until you wake it up again.
* Added parental control pin and settings to allow you to
  - lock to the starting bouquet only
  - lock the settings options.  
  The PIN is currently visibile in the settings screen when 
  unlocked and will be masked in a later release. 
* Re-architected player encapsulation and startup to allow for 
  an inline switch to the 'legacy' player to try and help with
  compatibilitiy on older model Samsung SMART TVs
* Added Advanced settings to allow you to change from the
  default video player to the legacy player that *might* be
  compatible with C/D Series SMART TVs.  The legacy player is
  only recommended for those with older model TVs, and is
  considered experimental with limited testing right now and
  comes with some known restrictions/limitations.
   - The info-bar overlay doesn't work
   - You have to return to the channel list (enter button) to
     select another channel
   - Skip doesn't work in recordings
   - Player doesn't buffer so might be susceptile to pauses
     if network is congested or slow
* Due to the large number of changes, the app will require you
  to re-input your settings on first execution following an
  install/update.   

+----------------------------------------------------------+                                                  
|                        2014                              |
+----------------------------------------------------------+
v4.0 - 27/12/2014
* Added paging of channel list using channel up/down in
  the list view screen. (Channel up/down still continues 
  to change channel in full screen play-back mode)
* Corrected video count positioning in list when more than
  100 channels are in a bouquet and you are playing a double
  or triple digit channel number in the list
* Fixed more service name picon issues with non
  alphanumeric characters
* Fixed previous/next indicators in channel list to 
  highlight when there are further pages of channels.
* Reformatted recordings list text to resolve positioning
  and wrap issues.
* Fixed pause on recordings play-back
* Now shows info-bar on pause, rewind, fast forward/skip
  when playing back a recording.
* Quick fix to prevent 0 items in recording list giving a
  problem - this needs a better fix to ignore empty 
  bouquets that will come in a future release.
* Resolved zap during full screen play back issue

v3.9 - 24/12/2014
* Rewrite of the sliding window channel view to  allow for
  easier future modifications

v3.8 - 21/12/2014
* Fixed settings save on application install/setup

v3.7 - 21/12/2014
* Resolved minor issue on settings screen. < & > were in 
  the wrong location following introduction of the zap 
  option
* Prevented Bouquet Description from wrapping when the
  description is unusually long
* Modified info bar for recordings to tidy up the progress
  bar and style it like the rest of the info bar, but also
  to resize recording description and show the time 
  remaining
* Corrected progress bar length to prevent run over out of
  the container
* Fixed buffering complete notification issue which
  prevented skip from working in recordings
* Introduced 'alert bar' to alert on conditions, the first
  of these being 'Cannot skip whilst buffering'
* Improved speed of EPG retrieval my making multiple async
  requests for now/next rather than one large request
* Blocked a number of the remote control keys when in full
  screen playback to prevent issues
* Modified start-up to avoid double load of channels/picon
  status check to speed things up and avoid issues with
  picon checks/display
* Improved picon handling by resolving some filename issues
  and avoiding a double check when changing from channels
  to recordings and back again
* Separated REST calls over different XMLHttpRequest objects
  to avoid the race conditions created with the introduction
  of the asynchronous timer code
* Separated AV Player implementation to allow future 
  switching to the legacy player.  These changes are not
  available yet as further modifications are required to
  re-enable the legacy player implementation
* Re-introduced the legacy player class but not enabled yet
  as the Samsung APIs clash meaning a change in player will
  need a restart of the app and this is still to be implemented 
* With the above, implementation of an advanced settings
  screen (yet to be enabled due to some issues) to select 
  the player
  
v3.6 - 19/12/2014
* Implemented zap to channel for those who want to force
  the channel change or have a single tuner
* Resolved further startup issues to resolve picon/stalling
  issues on startup
* Added more error handling to network communications to
  catch errors and allow for retry when failed
* Stopped settings screen losing focus when moving down from
  last field on initialisation
  
v3.5 - 14/12/2014
* Finalised audio channel switching.  Red button changes
  the audio channel during video playback when more than
  one audio stream is available.
* Notification to inform when change of audio stream
* If more than one audio stream is available this is
  highlighted in the infobar
* Implementation of a debug window/version to collect
  information from users when issues occur that are
  difficult to diagnose.  Press 'tools' to turn on 
  debug log.  For issues on startup you'll need to
  download the debug enabled build.
* Removed superfluous logging from all classes
  and standardised everywhere to use the new debug class
* Modified way the infobar timer works to fix an issue
  during change of channel where the infobar would close
  prematurely based on the timer from the previous channel
* Improved error handling in various scenarios
* Removed the number prefix question on startup to simplify
  the startup process.  This is still present in the 
  settings UI

v3.0 - 10/12/2014
* Modified navbar icons to nows how play button to 
  start channel stream. Enter still continues to function 
  as before for non H-series TVs.
* Added experimental audio channel switching by pressing
  red button when in full screen mode to cycle through
  audio channels 
* Detection of stream errors and display of screen to
  show error with option to retry or return to channel list
* Automated build process to simplify distribution
  
v2.8 - 08/12/2014
* Attempted to fix picon issues with 'unusual' responses
  from openwebif from some users
  
v2.7 - 07/12/2014
* Added OSD for Volume controls
* Modified CMD file to start installer to try and lcoate
  a compatible JRE to use
* Added Exit to the installer menu

v2.5 - 04/12/2014
* Fixed image paths on series F onwards
* Added Mute icon to show when mute is enabled
* Fixed minor issue in installer causing build problems

v2.4 - 04/12/2014
* Attempt to fix slow startup for some people

v2.3 - 03/12/2014
* Updated installer with many changes including:
  - New style
  - Top left dropdown Menu to select screens
  - Admin Screen to provide additional directory rather
    than having to do this on startup
  - Able to exit the installer from the web interface
  - About Screen
  - View of installation process as TV communicates
  - OpenWebIF Response Simulator to aid debugging of
    issues experienced by user.
    Needs files creating in c:\temp\e2logs as follows:
      > gettallservices.xml
      > getlocations.xml
      > movielist.xml
    These files correspond with the openwebif call with
    the same name and should contain a valid XML response.
* Updated E2Stream to handle recordings with no
  e2description element
* Added enter key unregister to try and fix another
  h-series only issue
* Tidy up removal of warnings to help spot issues more
  easily

v2.2 - 30/11/2014
* Removed all reference to SF Object
* Changed clock to absolute positioning
* Corrected play button to behave same as enter

v2.0.1 - 30/11/2014
* Removed screen saver disable to try to resolve 
  H series issues!

v2.0 - 29/11/2014
* Implemented volume up/down/mute to try and fix issue
  with H Series SMART TV models. OSD Still todo
* Changed start up routine massively to provide more
  information on startup and help optimise this.
* Fixed switch between TV/Recordings views
* Disabled settings change in recordings view
* Added clock to front-screen top right corner
* Changed infobar removal routine for stability
* Fixed scrolling issue on E series within recordings view

v1.8 - 26/11/2014
* Honour the ABM Hidden sections
* Many changes to the recordings playback including:
  - Progress Bar on info bar
  - Rw & Fw jump back/forwards 20 seconds
  - 1 and 3 jump back/forwards 30 seconds
  - 4 and 6 jump back/forwards 60 seconds
  - 7 and 9 jump back/forwards 120 seconds
  - Improved list view UI around recordings
  - Improved infobar view around recordings
  - When recording stops return to list
* Added Clock to the infobar showing current time

v1.7 - 23/11/2014
* Updated detection of separators and categories/headers in bouquets that has 
  been the primary cause of bugs surrounding missing picons.
* Add ignore of bouquet when there are no channels present
* Updated the display of channels to correct issues when number of channels 
  in a bouquet is less than 5.
* Added device id/picon mode to the about screen.

v1.6 - 23/11/2014
* Resolved issue with service id named picon files

v1.5 - 22/11/2014
* Readme Introduced

---------------------------------------------------------

E2Stream – the Enigma2 Samsung SMART TV Streaming App                  

Browse and watch channels from your Enigma2 set top box on a Samsung SMART TV connected to the same local network.

E2Stream allows you to stream/transcode channels from your Enigma2 Set top box to a Samsung SMART TV.

E2Stream requires:

- An Enigma 2 based set top box
- To be on the same network as the Samsung SMART TV
- To have Open WEBIF installed, enabled, running on port 80 and WITHOUT HTTP Authentication.
- Know the ports for streaming/transcoding (Default being 8001 and 8002 repectively)

Note – to control this, use UP/Down to browse the channel.  On moving through the list the NOW/NEXT updates on the right.

If you do use E2Stream, please consider donating an amount of your choice to support the continued development and hosting costs of this app!