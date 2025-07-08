package com.elite.elitebank.security

sealed class SecurityIssue {
    object None : SecurityIssue()
    object DebuggerAttached : SecurityIssue()
    object DeveloperOptionsEnabled : SecurityIssue()
    object DeviceRooted : SecurityIssue()
    object DeviceEmulator : SecurityIssue()
    object LoadFromGooglePlay : SecurityIssue()
    object CameraDetector : SecurityIssue()
    object MultiWindowModeDetector : SecurityIssue()
    object OverLayDetector : SecurityIssue()
}