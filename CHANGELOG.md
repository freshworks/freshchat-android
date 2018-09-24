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


