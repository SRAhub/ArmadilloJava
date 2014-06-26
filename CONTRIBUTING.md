Contributing to ArmadilloJava
=============================
- [Question or Problem?](#question)
- [Issues and Bugs](#issue)
- [Feature Requests](#feature)
- [Coding Guidelines](#coding)
- [Commit Guidelines](#commit)
- [Development Notes](#development)

<a name="question"></a> Got a Problem or Question?
--------------------------------------------------
If you have a question about how to use the ArmadilloJava linear algebra library, please direct these to [StackOverflow](http://stackoverflow.com/questions/tagged/armadillojava).

You may also want to check out our Getting Started.

<a name="issue"></a> Found an Issue or Bug?
-------------------------------------------
If you found a bug in the source code or a mistake in any kind of documentation, please **let us know** by adding an issue to the [Github Issue Tracker](https://github.com/SebastianNiemann/ArmadilloJava/issues).

You are welcomed to submit a pull request with your fix afterwards, if possible. **Please see our coding and commit guidelines below.**

<a name="feature"></a> Requesting a Feature?
--------------------------------------------
If you are missing some features within the ArmadilloJava linear algebra library feel free to ask us about it by adding a new request to the [Github Issue Tracker](https://github.com/SebastianNiemann/ArmadilloJava/issues) labelled `feature request`.

Note that a submitting a pull request providing the needed changes to introduced your requested feature usually speeds up the process. **Please see our coding and commit guidelines below.**

<a name="coding"></a> Coding Guidelines
---------------------------------------
- Everything must be **thoroughly tested** in compliance to our Master Test Plan.
- All methods must be **detailed documented**, regardless of their code visibility. This includes javadoc, as well as inline comments.
- We usually follow the [Google Style Guides](https://code.google.com/p/google-styleguide/).

<a name="commit"></a> Commit Guidelines
---------------------------------------
Commit messages must be organised as followed:
```
<type>: <subject>

<body>

<signature>
```

### Type
The `<type>` is used to define the nature of the commit. Stick to one of the following tags:

- **fix:** Adding fixes to the source code
- **feat:** Adding a new feature
- **doc:** Changes to the documentation
- **test:** Adding new tests
- **devel:** Changes concerning the development process (usually Travis CI)

### Subject
Add a short description (usually no more than 15 words) about the content  of your commit.

### Body
Describe the details of your changes as specific as possible. Do not be afraid to add multiple lines.

Please try to make your explanation as specific as possible and avoid vague descriptions.

If you submit a fix, include a list of fixed issues by adding:
```
Fixed:
#<issue number> <short issue description>
```

If you want to refer to a specific commit, add the SHA-1 hash together with the type and subject of the commit:
```
Commit <hash> (<type>: <subject>)
```

Feel free to read the read the [Linux Kernel commit guidelines](http://git.kernel.org/cgit/git/git.git/tree/Documentation/SubmittingPatches?id=HEAD) on how to improve commit messages. 

### Signature
You are required to end each commit message by adding the following DCO **and** signing your work. Official contributors may be exempt from the former.

```
Developer's Certificate of Origin 1.1

By making a contribution to this project, I certify that:

(a) The contribution was created in whole or in part by me and I
    have the right to submit it under the open source license
    indicated in the file; or

(b) The contribution is based upon previous work that, to the best
    of my knowledge, is covered under an appropriate open source
    license and I have the right under that license to submit that
    work with modifications, whether created in whole or in part
    by me, under the same open source license (unless I am
    permitted to submit under a different license), as indicated
    in the file; or

(c) The contribution was provided directly to me by some other
    person who certified (a), (b) or (c) and I have not modified
    it.

(d) I understand and agree that this project and the contribution
    are public and that a record of the contribution (including all
    personal information I submit with it, including my sign-off) is
    maintained indefinitely and may be redistributed consistent with
    this project or the open source license(s) involved.
```

The signature should start with `Signed-off-by:` and include your real name (no pseudonyms or anonymous contributions) followed by some contact information.
```
Signed-off-by: Random J Developer <random@developer.example.org>
```

<a name="development"></a> Development Notes
--------------------------------------------
We strive for an agile development process with monthly sprint meetings. New tasks are added any time via the [Github Issue Tracker](https://github.com/SebastianNiemann/ArmadilloJava/issues) and assigned in the beginning of each sprint via [Waffle.io](https://waffle.io/sebastianniemann/onlineoptimisation). 

Our repository defines four (branch) types, called:
- master
- tag
- `<user story branch>`
- `<hotfix branch>`

### Master
The main development branch, including the current state of the next release. Small changes may be pushed directly into this branch. The last task of each sprint is releasing the latests changes into `tag`.

**Lifetime:** Infinite
**Continuous integration:** Yes
**Continuous deployment:** Yes

### Tag
Previous releases, copied directly from `master`. A specific version may be cloned to create a `<hotfix branch>`.

**Lifetime:** Infinite
**Continuous integration:** No (direct copy of the master branch)
**Continuous deployment:** Yes, manually

### User Story Branch
Each sprint may add several, individual `<user story branches>`, per user story. After completion of all tasks, each `<user story branch>` is merged into the `master` branch and removed afterwards.

**Lifetime:** One sprint
**Continuous integration:** No
**Continuous deployment:** No

### Hotfix Branch
Only used to test hotfixes to be applied to previous releases in `tag` and removed after releasing a fix.

**Lifetime:** Very short, 2 days at most
**Continuous integration:** No
**Continuous deployment:** No
