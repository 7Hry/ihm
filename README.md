# BLOCK BLAST GOD MODE SUPREMO

Android native Java starter for Android 8+ (target SDK 35).

## Current implementation
- Android app shell and permission entry points.
- MediaProjection consent flow and foreground service shell.
- Movable floating HUD.
- Accessibility service declaration with gesture capability enabled.
- Manual 8x8 board format validation screen.
- GitHub Actions workflow to build and upload a debug APK.

## Not implemented / not validated yet
- Automatic 8x8 board image recognition.
- Recognition of the three offered pieces.
- Full placement search and scoring for Block Blast.
- Green recommended-placement overlay over the live game.
- Per-move confirmation and gesture execution.
- Runtime validation on a Samsung Galaxy A16 / Android 16.

This is a buildable starting point, not a completed game assistant. The workflow produces an installable debug APK, but the features above must be implemented and tested before calling the app complete.