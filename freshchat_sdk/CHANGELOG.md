## 5.4.2(2023-03-06)

### Bug Fixes
* Fix for send button being enabled when only empty spaces are entered
* Fix for extra space being displayed below multiline bot messages
* Fix for a crash which occurs while attaching images

## 5.4.1(2023-01-11)

### Bug Fix
* Fix Android bot stability issues

## 5.4.0(2022-12-19)

### Feature
* Support read only and single select carousel in bot flow

## 5.3.3(2022-12-05)

### Hot Fix
* Fix for agent/bot message timestamp aligning to the right end of the message bubble

## 5.3.2(2022-11-16)

### Enhancement
* Updated targetSdkVersion to Android 12

### Bug Fixes
* Fix for the next bot flow to trigger on selecting carousel.
* Fix for clearing error message for invalid input in bot flow.
* Fix for handling empty messages.
* Fix for handling quick actions menu overlapping in landscape mode.
* Fix for custom attachment icons appearing too large in v5.3.0 and v5.3.1.

## 5.3.1(2022-10-18)

### Bug Fix
* Fix for keyboard hiding last few messages

## 5.3.0(2022-09-27)

### Feature
* Support for upload file for bot flows and attachment option

## 5.2.0(2022-09-13)

### Feature
* Support for phone number, email and number input types in bot flow

## 5.1.2(2022-08-23)

### Bug fix
* Fix for a crash on quick option buttons which was introduced in version 5.1.0

## 5.1.1(2022-08-11)

### Bug fix
* Fix for orientation change crash which was introduced in version 5.1.0

## 5.1.0(2022-07-11)

### Enhancement
* Support for quick options in bot flow

### Bug fix
* Fix for ConnectivityManager leak

## 5.0.5(2022-02-04)

### Bug fix
* Fix for "Notification not posted to system tray when app is in background"
* JWT - Issue with decoding token containing '-' or '_' in reference

## 5.0.4(2022-02-04)

### Bug fix
* Fix for dismissing keyboard when moving from Chat screen to App screen.
* Fix for handing empty CSAT

## 5.0.3(2021-12-23)

### Bug fix
* Fix for displaying notifications in Android 12

## 5.0.2(2021-12-03)

### Bug fix
* Fix for Topic name and image being empty

## 5.0.1(2021-11-15)

### Enhancement
* Display complete name for messages created using API

## 5.0.0(2021-10-27)

### Enhancement
* Support for Bots created using bots builder

## 4.4.0(2021-10-04)

### Build Changes
* Upgraded target version to 30

### Bug fix
* Fix for image capture crash on lower resolution devices

## 4.3.9(2021-09-30)

### Enhancement
* Added instrumentation for SDK

## 4.3.8(2021-09-07)

### Bug fix
* Freshchat account reactivation issue fix

## 4.3.6(2021-08-31)

### Bug fix
* Fix to display custom category icons for FAQ categories

## 4.3.5(2021-06-18)

### Bug fix
* Fix for data encryption issue due to changes made in v4.3.2

## 4.3.4(2021-05-10)

### Bug fix
* Fixed FAQ notification click not redirecting to chat screen
* Fixed message notifications are not getting dismissed on chat screen open

## 4.3.3(2021-03-18)

### Enhancement
* Upgraded picasso library version to 2.8

## 4.3.2(2021-03-01)

### Enhancement
* Updated cryptographic encryption pattern used for encrypting shared preferences

## 4.3.1(2021-02-24)

### Enhancement
* Updated FAQ search empty state UI

### Bug fix
* Support for custom reply text in quick reply options

## 4.3.0(2021-02-02)
### Enhancement
* Omni Kbase support for bundled accounts

## 4.2.0(2020-12-11)
### Enhancement
* Updated the algorithm used for encrypting Shared Preferences

## 4.1.1(2020-12-07)
### Bug Fix
* Fixed push registration token set issue during user restore

## 4.1.0(2020-11-10)
### Enhancement
* Upgraded target SDK version of SDK to Android 29

## 4.0.0(2020-10-29)
### Enhancement
* Support for live translation of messages

## 3.6.8(2020-10-15)
### Enhancement
* Support for custom line spacing of messages in chat screen

## 3.6.7(2020-10-1)

### Enhancement
* Alert to notify when domain is invalid

## 3.6.6(2020-09-22)

### Bug Fix
* Allowed "-" in user property key

## 3.6.5(2020-09-08)

### Bug Fix
* Removed search icon when FAQ search is open
* Added "Still looking for help? Talk to us" string for FAQ downvote.

## 3.6.4(2020-08-03)

### Bug Fix
* Fix to honor showContactUsOnFaqNotHelpful config

## 3.6.3(2020-07-22)

### Enhancement
* Performance improvements in restoring a user

### Bug Fix
* Remove cache file from external storage 

## 3.6.2(2020-07-3)

### Enhancement
* Performance improvements in messages rendering time

## 3.6.1(2020-06-23)

### Bug Fix
* Fix to load messages quicker

## 3.6.0(2020-06-12)

### Enhancement
* Support to book meetings

### Bug Fix
* Show agent first name alone instead of full name
* Fixed image resize issue

## 3.5.1(2020-05-12)

### Bug Fix
* Fix to display agent message in Carousel message

## 3.5.0(2020-05-07)

### Enhancement
* Support for messages with Carousel options

## 3.4.0(2020-04-30)

### Enhancement
* Support for dropdown options message

## 3.3.0(2020-03-30)

### Enhancement
* Optimised SDK initialisation when app is in background

## 3.2.2(2020-03-04)

### Bug Fix
* Fixed display order of articles

## 3.2.1(2020-02-03)

### Bug Fix
* Fixed locale resetting issue on orientation change
* Fixed a redirection issue in FAQs search

## 3.2.0(2020-01-30)

### Enhancement
* Introducing changes in MUV calculation to take care of cases where customer's SDK implementation is not optimised. This would bring down the unique visitors count.

## 3.1.0(2020-01-23)

### Feature
* Add user events from your app to Freshchat timeline to give your agents context on user's journey

## 3.0.0(2019-11-12)

### Enhancement
* Ability to search through FAQs filtered by tags
* Increased CSAT message view maximum height to 4 lines
* Ability to handle channels deeplinks

### Build Changes
* Upgraded target version to 28
* Updated android gradle plugin to 3.2.1 and Gradle to 4.7

## 2.9.0 (2019-9-25)

### Enhancement
* Support for showing proactive reply suggestions

## 2.8.0 (2019-9-18)

### Enhancement
* Support for locale change at run time

## 2.7.1 (2019-9-3)

### Bug Fix
* Fix JSONException in FAQs flow (introduced in 2.7.0 version).

## 2.7.0 (2019-8-13)

### Enhancement
* Added more Freshchat SDK events
* Broadcast SDK properties along with events

### Breaking Change
* In order to support rich events, we have added Freshchat.getEventFromBundle() API and removed FreshchatEvent.fromAction(action) API.

## 2.6.1 (2019-5-31)

### Enhancement
* Once the article is updated, users will again see the voting option.
* Also users will be able to message you from the downvoted article screen, even if they leave and come back again.

## 2.6.0 (2019-5-30)

### Enhancement
* Support to set importance of Freshchat Notification Channels using FreshchatNotificationConfig.setImportance API.

## 2.5.2 (2019-5-22)

### Bug Fix
* Stopped message list from auto scrolling to bottom
* Improvements in JWT timer timeout logic

## 2.5.1 (2019-5-20)

### Bug Fix
* Removed unused log

## 2.5.0 (2019-5-10)

### Bug Fix
* Open SDK screens in same task irrespective of the context passed

## 2.4.1 (2019-3-7)

### Enhancements
* Improvements in logic to load new messages for conversations

## 2.4.0 (2019-2-15)

### Enhancements
* Broadcast screen transition event from all Freshchat screens

## 2.3.0 (2019-2-4)

### Enhancements
* Graceful handling of unsupported messages

### Bug Fixes
* Trigger message received event only when a new message is received

## 2.2.0 (2019-1-7)

### Enhancements
* Ability to configure custom response expectations and away message for channels

### Bug Fixes
* Show loading when image is being loaded in Picture preview screen

### Breaking Change
* If you have modified strings.xml to hide response expectation messages, it will continue to work in all cases except when away. You can set `responseExpectationEnabled` flag in FreshchatConfig as `false` to completely hide response expectations.

## 2.1.0 (2018-12-28)

### Enhancements
* Ability to mask sensitive data present in user message

## 2.0.0 (2018-12-26)

### Enhancements
* Ability to intercept all external links
* Securely identify and restore users using Id Tokens (JWT)
* Ability to intercept Freshchat notification
* Ability to listen to Freshchat events like message sent, message received, etc.

### Breaking Change
* Freshchat.handleGcmMessage API has been removed.
* Change in method signature for Freshchat.handleFcmMessage API, now requires context as a param.

## 1.5.3 (2018-10-12)

### Enhancements
* Allow reuse of deeplink scheme from message in FAQs

### Bug Fixes
* Add proguard rules to detect presence of Picasso

## 1.5.2 (2018-09-21)

### Enhancements
* Support for using alternate image loader libraries - via [Freshchat.setImageLoader()](https://support.freshchat.com/support/solutions/articles/229319-freshchat-android-sdk-integration-steps#custom-image-loader) - SDK defaults to including Picasso 2.5.2
* In Android N+, support for auto retry messages on chat screen based on connectivity changes 

### Bug Fixes
* Fix crash while transforming downloaded image into circular shape
* Maintain order of the messages when retrying failed messages

## 1.5.1 (2018-08-29)

### Bug Fixes
* Fix date format for messages sent in previous years

## 1.5.0 (2018-07-26)

### Enhancements

* Support for auto expiring customer satisfaction surveys
* Make back navigation launch app by default when chat screen is opened from notification

### Changes
* Agent avatar behaviour change - specific order of fallback (agent avatar, then custom static icon, then app icon)

### Bug Fixes
* Play default notification tone if no notification tone is set
* Fix representation of message being copied to clipboard
* Disallow attachment of non image file types (Prefers document provider where possible)
* Fix rendering of newlines in message text
* Fixes issue [#9](https://github.com/freshdesk/freshchat-android/issues/9)

### Deprecated
* Freshchat.FRESHCHAT_ACTION_MESSAGE_COUNT_CHANGED has been deprecated in favour of Freshchat.FRESHCHAT_UNREAD_MESSAGE_COUNT_CHANGED

## 1.4.2 (2018-06-13)

### Enhancements
* Add support for overriding csat survey question

## 1.4.1 (2018-06-04)

### Bug Fixes
* Fixes issue with read receipt not being acked and retried

## 1.4.0 (2018-05-24)

### Enhancements
* Graceful handling of deleted users and accounts

### Bug Fixes
* Fixes proguard rules after VerifyError fix

## 1.3.5 (2018-05-24)

### Bug Fixes
* Fix VerifyError while running Junit tests with Robolectric

## 1.3.4 (2018-04-13)

### Bug Fixes
* Fix linkify breaking links in message text

## 1.3.3 (2018-03-20)

### Enhancements
* Support for historical response time if current response time is not available

### Bug Fixes
* Dismissing previous user's unread notifications when user is reset

## 1.3.2 (2018-03-19)

### Enhancements
* Optimize background restarts based on backlogs

## 1.3.1 (2018-02-02)

### Enhancements
* Support for notification channels in Oreo
* Support for customising notification tones
* RTL Improvements
    * Prefer app locale over device locale
    * Display unread count in user locale

### Bug Fixes
* Fix notification token not being set for a restored user
* Fix gallery disable flag not being respected when camera capture is enabled
* Fix some user updates post identifyUser() triggering user restore inadvertently

## 1.3.0 (2018-01-04)

### Enhancements
Rendering support for RTL content

### Bug Fixes
Fix intermittent DB cursor leak

## 1.2.2 (2018-12-15)

### Bug Fixes
* Fix phone number & country code incorrectly being returned in getUser()
* Fix FAQ content being hidden by voting view in some cases

## 1.2.1 (2017-11-17)

### Changes
* Change in content refresh intervals

### Bug Fixes
* Fix tag case sensitivity when filtering FAQs/Channels

## 1.2.0 (2017-11-16)

### Enhancements
* Ability to restore user and conversations across devices/sessions/platforms

## 1.1.1 (2017-11-03)

### Changes
* Update default proguard rules for external dependencies

### Bug Fixes
* Fixes notifications not being displayed for in-app campaigns
* Fixes issue with on demand/just in time SDK initialization

## 1.1.0 (2017-10-20)

### Enhancements
* Support for theming various UI elements
* Ability to display team member name and avatar

### Bug Fixes
* Fixes custom font breakage in specific views

## 1.0.0 (2017-09-18)
* Freshchat Android SDK first version


