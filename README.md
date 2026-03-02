# MineSweeper

## Status

This project is discontinued and archived as of March 2, 2026.

It is kept online as a small Java/Swing Minesweeper prototype and reference project. No new features are planned, and only minimal cleanup has been done to keep the repository readable and runnable.

## Overview

MineSweeper is a simple Java implementation of Minesweeper with:

- a text-based player in [`src/User.java`](./src/User.java)
- a Swing-based GUI in [`src/GraphUser.java`](./src/GraphUser.java)
- core game logic under [`src/gameCore`](./src/gameCore)

The repository does not use Maven or Gradle. It is a plain Java project that was originally opened in Eclipse/IntelliJ.

## Project Layout

- `src/gameCore`: board, cell, game loop, and shared API
- `src/MainTest.java`: launches the GUI version
- `src/DebugMain.java`: simple debugging / distribution experiment
- `bin`: previously compiled classes kept from the original project history

For source changes, treat `src/` as the authoritative code.

## Running

From PowerShell:

```powershell
javac -d out (Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName })
java -cp out MainTest
```

The default entry point opens the Swing UI.

## Notes

- This is an old learning project, not a polished or actively maintained game.
- The codebase now includes a few low-risk fixes, but it has not been modernized.
- If you want a production-ready Minesweeper implementation, use this repository as a reference rather than a dependency.

## License

This repository is available under the MIT License. See [`LICENSE`](./LICENSE).
