# Tetris: A Java/Git exercise

## Summary

This repository contains a basic implementation of a Tetris-like game.
It currently lacks many features of the original game, some of which
are described in the issues. Your task is to:

1. pick an issue that doesn't have an owner yet (unassigned),
   and assign yourself to it.
   * If there's no unassigned issue left, you can pick one of the
     assigned. Just let us know in a comment to that issue that you're
     going to work on it as well.
1. create a feature branch with a name following a convention:
   ```
   issueID-short-but-meaningful-description-written-in-kebab-case
   ```
   * A person that is not set the main owner of the issue should take
     extra care such that the feature branch name does not collide
     with the primary owner's branch. Don't push the changes to the same
     branch.
1. implement the feature,
1. push the changes to a remote feature branch,
1. create a Pull Request to merge it to `master`.

## Rules

1. Your implementation shall not break any of the existing functionalities.
1. The solution shall be delivered in the form of self-contained commits.
   Each commit contains:
   
   * a consistent, working set of changes,
   * a set of tests that prove the changes actually work,
   * a documentation attached to public API,
   * a commit message reasonably explaining the changes.

1. You shall touch only those classes that are necessary for the purpose
   of resolving the issue. Too wide, especially unrelated changes may cause
   the PR to be rejected.
1. Feel free to add missing tests and Javadocs.
