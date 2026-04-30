# Git - April 30, 2026

A comprehensive reference guide for Git commands, workflows, and concepts.

---

## Table of Contents

1. [Git Fundamentals](#git-fundamentals)
2. [Installing Git](#installing-git)
3. [Git Configuration](#git-configuration)
4. [Getting Started](#getting-started)
5. [Basic Workflow](#basic-workflow)
6. [Viewing Changes](#viewing-changes)
7. [Inspecting History](#inspecting-history)
8. [Remote Operations](#remote-operations)
9. [Branching & Merging](#branching--merging)
10. [Housekeeping](#housekeeping)
11. [Advanced Operations](#advanced-operations)
12. [Common Workflows](#common-workflows)
13. [Quick Reference Cheat Sheet](#quick-reference-cheat-sheet)

---

## Git Fundamentals

### Three States of a File

Every file in a Git repository exists in one of three states:

```
Untracked ──[git add]──> Staged ──[git commit]──> Committed
     │                       │                         │
     └── Not in repo ─── Pending ──── Saved locally ───┘
```

| State       | Description |
|-------------|-------------|
| **Untracked** | New file not yet added to Git |
| **Staged**    | Marked for inclusion in the next commit |
| **Committed** | Saved to the local repository history |

### Three Areas of Git

Git operates across three working areas:

```
┌─────────────────┐     ┌─────────────────┐     ┌─────────────────┐
│  Working         │     │  Staging         │     │  .git            │
│  Directory       │────>│  Area            │────>│  Repository      │
│                  │     │  (Index)         │     │                  │
│  Edit files      │     │  Prepare commits │     │  Committed data  │
└─────────────────┘     └─────────────────┘     └─────────────────┘
       [git add]              [git commit]
```

| Area | Description |
|------|-------------|
| **Working Directory** | Where you edit files locally |
| **Staging Area (Index)** | Where changes are prepared for commit |
| **.git Repository** | Where committed snapshots are permanently stored |

---

## Installing Git

### Linux (Debian/Ubuntu)

```bash
sudo apt-get install git
```

### macOS

```bash
brew install git
```

Or download from: https://git-scm.com/download/mac

### Windows

Download from: https://git-scm.com/download/win

Or via PowerShell (winget):

```powershell
winget install --id Git.Git -e --source winget
```

### Code Hosting Providers

| Provider   | URL |
|------------|-----|
| GitHub     | https://github.com |
| GitLab     | https://gitlab.com |
| Bitbucket  | https://bitbucket.org |

---

## Git Configuration

### Initial Setup

Set your identity (required before creating commits):

```bash
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
```

**Global** means this applies to all repositories. To set for a single repo, omit `--global`.

### Configuration Commands

| Command | Description |
|---------|-------------|
| `git config --list` | List all configuration settings |
| `git config user.name` | Show configured username |
| `git config user.email` | Show configured email |
| `git config --list --global` | Show only global configuration |

### Verify Installation

```bash
git --version
```

### Getting Help

```bash
man git           # Full manual
git help          # General help
git help <cmd>    # Specific command help (e.g., git help commit)
git <cmd> -h      # Quick help for a command
```

---

## Getting Started

### Initialize a New Repository

Create a new Git repository in the current directory:

```bash
mkdir my-project
cd my-project
git init
```

This creates a hidden `.git` folder containing all repository data.

### Clone an Existing Repository

Copy a remote repository to your local machine:

```bash
git clone https://github.com/user/repo.git
```

Or with a specific directory name:

```bash
git clone https://github.com/user/repo.git my-folder
```

### Verify Repository Status

```bash
git status
```

#### Short Status

```bash
git status -s
```

| Symbol | Meaning |
|--------|---------|
| `M` | Modified file |
| `A` | Added to staging area |
| `??` | New file, untracked by Git |
| `D` | Deleted |
| `R` | Renamed |
| `MM` | Modified in working directory AND staging area |

---

## Basic Workflow

### Staging Changes

Stage individual files:

```bash
git add <file>
git add index.js
```

Stage all changes:

```bash
git add .
```

### Unstage a File

Remove a file from the staging area (keeps changes in working directory):

```bash
git restore --staged <file>
```

Older syntax (still works):

```bash
git reset HEAD <file>
```

### Commit Changes

Save staged changes to the repository:

```bash
git commit -m "Your commit message"
```

Stage ALL tracked files and commit in one step:

```bash
git commit -a -m "Your commit message"
```

> **Note:** The `-a` flag only stages modified tracked files. New (untracked) files still need `git add` first.

### Edit the Last Commit

```bash
git commit --amend -m "Updated commit message"
```

> **Caution:** Only amend if the commit hasn't been pushed to a shared remote.

---

## Viewing Changes

### Git Diff

Compare changes between areas:

| Command | What it shows |
|---------|---------------|
| `git diff` | Changes in working directory vs staging area (unstaged changes) |
| `git diff --staged` | Changes in staging area vs last commit (staged changes) |
| `git diff HEAD` | All changes since last commit (both staged and unstaged) |
| `git diff <commit> <file>` | Changes in a file vs a specific commit |
| `git diff --staged --no-renames` | Staged changes without rename detection |

**Example:**

```bash
git diff                   # See unstaged changes
git diff --staged          # See what will be committed
git diff HEAD              # See all changes since last commit
```

### Windows PowerShell Equivalent

Viewing a file:

```powershell
Get-Content <file>         # Display file contents (PowerShell)
cat <file>                  # Alias in PowerShell
type <file>                 # CMD equivalent
```

---

## Inspecting History

### Git Log

View commit history:

```bash
git log
```

| Flag | Description |
|------|-------------|
| `git log -1` | Show only the last 1 commit |
| `git log -5` | Show the last 5 commits |
| `git log --oneline` | Condensed one-line format |
| `git log --stat` | Show files changed per commit |
| `git log --patch` | Show full diff of each commit |
| `git log --graph` | Visual branch/merge graph |
| `git log --author="Name"` | Filter by author |
| `git log --since="2026-01-01"` | Filter by date |

**Combined example:**

```bash
git log --oneline --graph --all --decorate
```

### Other Inspection Commands

| Command | Description |
|---------|-------------|
| `git show <commit>` | Show details of a specific commit |
| `git show HEAD` | Show the latest commit |
| `git show HEAD~2` | Show 2 commits back |
| `git blame <file>` | Show who last modified each line |

---

## Remote Operations

### Remote Management

| Command | Description |
|---------|-------------|
| `git remote` | List remotes |
| `git remote -v` | List remotes with URLs |
| `git remote add origin <url>` | Add a remote repository |
| `git remote remove origin` | Remove a remote |

### Push Changes

Upload local commits to a remote repository:

```bash
git push -u origin main
```

| Flag | Description |
|------|-------------|
| `-u` | Sets upstream tracking (first push) |
| `origin` | Remote name |
| `main` | Branch name |

Subsequent pushes (after `-u` is set):

```bash
git push
```

### Pull Changes

Fetch and merge remote changes:

```bash
git pull origin main
```

### Fetch Changes

Download remote changes without merging:

```bash
git fetch origin
```

This lets you review changes before merging manually.

---

## Branching & Merging

### Branch Workflow Visualization

```
           ┌─── feature ──────────────────┐
          /                                  \
main ────●────────────●──────────────────────●────▶
              \              \                /
               └─── bugfix ───┘─────────────┘
```

### Create & Switch Branches

**Modern approach (recommended):**

```bash
git branch feature         # Create a new branch
git switch feature         # Switch to it
git switch -c feature      # Create and switch in one command
git switch main            # Switch back to main
```

**Older syntax (still works):**

```bash
git branch feature         # Create branch
git checkout feature       # Switch to it
git checkout -b feature    # Create and switch
git checkout main          # Switch back
```

### List Branches

```bash
git branch                 # Local branches
git branch -a              # All branches (local + remote)
git branch -r              # Remote branches only
git branch -v              # Verbose (shows last commit per branch)
```

### Delete Branches

```bash
git branch -d feature      # Delete merged branch (safe)
git branch -D feature      # Force delete (even if not merged)
```

### Rename a Branch

```bash
git branch -m <old-name> <new-name>
```

Or rename the current branch:

```bash
git branch -m <new-name>
```

### Merge Branches

Merge a branch into the current branch:

```bash
git switch main
git merge feature
```

**Merge types:**

```
Fast-forward (no divergent commits):

main ─────●──────●
                  ↑ feature
Result: main pointer moves forward

Three-way merge (divergent histories):

main ─────●──────●─────────● (merge commit)
                  \        /
feature            ●──────●
```

### Resolve Merge Conflicts

When Git can't automatically merge, it marks conflicts in the file:

```
<<<<<<< HEAD
Current branch content
=======
Incoming branch content
>>>>>>> feature
```

To resolve:
1. Edit the file to keep the correct content
2. Remove conflict markers
3. Stage and commit:

```bash
git add <file>
git commit -m "Resolve merge conflict"
```

---

## Housekeeping

### Stashing

Temporarily save uncommitted changes:

| Command | Description |
|---------|-------------|
| `git stash` | Stash all tracked changes |
| `git stash push -m "description"` | Stash with a message |
| `git stash list` | List all stashes |
| `git stash show -p` | Show stash contents with diff |
| `git stash apply` | Apply latest stash (keeps stash) |
| `git stash pop` | Apply latest stash and remove it |
| `git stash drop` | Delete the latest stash |
| `git stash clear` | Delete all stashes |

### Remove Files

| Command | Description |
|---------|-------------|
| `git rm <file>` | Remove file from tracking AND disk |
| `git rm --cached <file>` | Untrack file but keep it on disk |
| `git rm -r <dir>` | Remove directory recursively |

### Rename/Move Files

```bash
git mv <old-name> <new-name>
```

This stages the rename automatically.

### Restore Files

Discard changes in working directory:

```bash
git restore <file>
```

Restore a file to a specific commit:

```bash
git restore --source=<commit> <file>
```

---

## Advanced Operations

### Git Reset

Move HEAD and optionally modify staging area/working directory:

| Command | Effect |
|---------|--------|
| `git reset <commit>` | Move HEAD; keep changes in working directory |
| `git reset --soft <commit>` | Move HEAD; keep changes staged |
| `git reset --mixed <commit>` | Same as default; changes unstaged |
| `git reset --hard <commit>` | Move HEAD; discard ALL changes |

> **Caution:** `--hard` permanently deletes uncommitted changes.

**Example - Undo last commit but keep changes:**

```bash
git reset --soft HEAD~1
```

**Example - Completely discard last commit:**

```bash
git reset --hard HEAD~1
```

### Git Rebase

Reapply commits on top of another base:

```bash
git switch feature
git rebase main
```

This replays your feature branch commits onto the latest main, creating a linear history.

```
Before rebase:              After rebase:
main ──●──●                 main ──●──●──●──●
           \                              (feature commits replayed)
feature      ●──●          
```

**Interactive rebase (edit commit history):**

```bash
git rebase -i HEAD~3
```

This opens an editor where you can squash, reorder, or edit commits.

### Git Reflog

View the history of HEAD movements (useful for recovery):

```bash
git reflog
```

Recover a "lost" commit:

```bash
git reset --hard HEAD@{2}
```

---

## Common Workflows

### Workflow 1: Clone → Edit → Commit → Push

Complete workflow for contributing to an existing project:

```bash
# 1. Clone the repository
git clone https://github.com/user/repo.git
cd repo

# 2. Create a feature branch
git switch -c feature/login-page

# 3. Make changes to files
# ... edit files ...

# 4. Check what changed
git status
git diff

# 5. Stage and commit
git add .
git commit -m "Add login page component"

# 6. Push to remote
git push -u origin feature/login-page
```

### Workflow 2: Create Branch → Work → Merge to Main

Working on a feature and merging back:

```bash
# 1. Make sure main is up to date
git switch main
git pull origin main

# 2. Create and switch to feature branch
git switch -c feature/user-auth

# 3. Work on feature
# ... make changes ...
git add .
git commit -m "Implement user authentication"

# 4. Keep feature branch updated with main
git fetch origin
git rebase origin/main

# 5. Push feature branch
git push -u origin feature/user-auth

# 6. After PR is approved, merge (or use GitHub UI)
git switch main
git merge feature/user-auth
git push origin main

# 7. Clean up
git branch -d feature/user-auth
git push origin --delete feature/user-auth
```

### Workflow 3: Stash → Switch Branch → Pop

Handling interruptions (e.g., urgent bugfix):

```bash
# 1. You're working on feature but need to switch
git stash push -m "WIP: feature in progress"

# 2. Switch to main and create bugfix branch
git switch main
git pull origin main
git switch -c bugfix/critical-fix

# 3. Fix the bug
# ... make changes ...
git add .
git commit -m "Fix critical bug"

# 4. Push and create PR
git push -u origin bugfix/critical-fix

# 5. Switch back to feature
git switch feature/user-auth

# 6. Restore your stashed work
git stash pop
```

### Workflow 4: Undo a Commit

Undoing mistakes safely:

```bash
# Undo last commit, keep changes staged
git reset --soft HEAD~1

# Undo last commit, keep changes unstaged
git reset HEAD~1

# Undo last commit, discard all changes (CAREFUL!)
git reset --hard HEAD~1

# Revert a commit (creates a new commit that undoes changes)
git revert <commit-hash>
```

> **Tip:** Use `git revert` for shared branches—it's safer than `reset` because it doesn't rewrite history.

### Workflow 5: Resolving Merge Conflicts

Step-by-step conflict resolution:

```bash
# 1. Merge and encounter conflict
git switch main
git merge feature/branch
# CONFLICT (content): Merge conflict in src/app.js

# 2. See conflicted files
git status

# 3. Open and edit the conflicted file
# Look for conflict markers:
# <<<<<<< HEAD
# (current branch content)
# =======
# (incoming branch content)
# >>>>>>> feature/branch

# 4. After resolving, mark as resolved
git add src/app.js

# 5. Commit the merge
git commit -m "Merge feature/branch, resolve conflicts in app.js"
```

---

## Quick Reference Cheat Sheet

### Setup & Configuration

```bash
git config --global user.name "Name"       # Set username
git config --global user.email "email"      # Set email
git config --list                           # View all config
```

### Creating Repos

```bash
git init                                    # Initialize new repo
git clone <url>                             # Clone remote repo
git clone <url> <dir>                       # Clone to specific directory
```

### Staging & Committing

```bash
git add <file>                              # Stage a file
git add .                                   # Stage all changes
git commit -m "msg"                         # Commit staged changes
git commit -a -m "msg"                      # Stage tracked + commit
git commit --amend -m "msg"                 # Amend last commit
```

### Branching

```bash
git branch                                  # List local branches
git branch <name>                           # Create branch
git switch <name>                           # Switch branch (modern)
git switch -c <name>                        # Create + switch
git checkout -b <name>                      # Create + switch (legacy)
git branch -d <name>                        # Delete merged branch
git branch -D <name>                        # Force delete branch
```

### Remote Operations

```bash
git remote -v                               # List remotes
git remote add origin <url>                 # Add remote
git push -u origin main                     # Push (first time)
git push                                    # Push (subsequent)
git pull origin main                        # Pull changes
git fetch origin                            # Fetch without merging
```

### Viewing Changes

```bash
git status                                  # Working directory status
git status -s                               # Short status
git diff                                    # Unstaged changes
git diff --staged                           # Staged changes
git diff HEAD                               # All changes since last commit
git log                                     # Commit history
git log --oneline --graph                   # Visual history
git log --stat                              # Files changed per commit
```

### Undoing Changes

```bash
git restore <file>                          # Discard working changes
git restore --staged <file>                 # Unstage a file
git reset --soft HEAD~1                     # Undo commit, keep staged
git reset HEAD~1                            # Undo commit, keep changes
git reset --hard HEAD~1                     # Undo commit, discard changes
git revert <commit>                         # Revert with new commit
```

### Stashing

```bash
git stash                                   # Stash changes
git stash push -m "msg"                    # Stash with message
git stash list                              # List stashes
git stash pop                               # Apply and remove stash
git stash apply                             # Apply without removing
git stash drop                              # Delete latest stash
```

### Merging & Rebasing

```bash
git merge <branch>                          # Merge branch into current
git rebase <branch>                         # Rebase current onto branch
git rebase -i HEAD~<n>                      # Interactive rebase
```

### File Operations

```bash
git rm <file>                               # Remove file (staged)
git rm --cached <file>                      # Untrack file, keep on disk
git mv <old> <new>                          # Rename/move file
```

### Inspecting

```bash
git show <commit>                           # Show commit details
git blame <file>                            # Show line-by-line authorship
git reflog                                  # Show HEAD history
```

---

## Windows PowerShell Quick Reference

| Action | Command |
|--------|---------|
| Change directory | `Set-Location <path>` or `cd <path>` |
| List files | `Get-ChildItem` or `ls` or `dir` |
| Create file | `New-Item -ItemType File <name>` or `ni <name>` |
| Create directory | `New-Item -ItemType Directory <name>` or `mkdir <name>` |
| Print working directory | `Get-Location` or `pwd` |
| View file contents | `Get-Content <file>` or `cat <file>` |
| Move/rename file | `Move-Item <source> <dest>` or `mv <source> <dest>` |
| Copy file | `Copy-Item <source> <dest>` or `cp <source> <dest>` |
| Delete file | `Remove-Item <file>` or `rm <file>` |

---

## Tips & Best Practices

1. **Commit often** — Small, focused commits are easier to understand and revert
2. **Write good commit messages** — Use imperative mood ("Add feature" not "Added feature")
3. **Pull before push** — Always `git pull` before `git push` to avoid conflicts
4. **Use branches** — Never work directly on `main`
5. **Don't commit secrets** — Never commit `.env`, API keys, or credentials
6. **Use `.gitignore`** — Tell Git which files to ignore
7. **Rebase for clean history** — Use `git rebase` before merging feature branches
8. **Use `git stash`** — Save work in progress without committing

---

*Last updated: April 30, 2026*
