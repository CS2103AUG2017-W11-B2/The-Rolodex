# jo-lyn-unused
###### \java\seedu\address\model\ModelManagerTest.java
``` java
    // Written as a warm up at the start of phase B and before features were finalised
    @Test
    public void removeTag() throws IllegalValueException, PersonNotFoundException {

        Set<Tag> tagSet1 = SampleDataUtil.getTagSet("friends", "classmate");
        Set<Tag> tagSet2 = SampleDataUtil.getTagSet("owesMoney", "classmate");

        Person person1 = new Person(ALICE);
        person1.setTags(tagSet1);

        Person person2 = new Person(BENSON);
        person2.setTags(tagSet2);

        Rolodex rolodex = new RolodexBuilder().withPerson(person1).withPerson(person2).build();
        UserPrefs userPrefs = new UserPrefs();

        ModelManager modelManager = new ModelManager(rolodex, userPrefs);

        Tag tagToRemove = new Tag("classmate");
        modelManager.removeTag(tagToRemove);

        Set<Tag> tagSet1New = SampleDataUtil.getTagSet("friends");
        Set<Tag> tagSet2New = SampleDataUtil.getTagSet("owesMoney");

        Person person1New = new Person(ALICE);
        person1.setTags(tagSet1New);
        Person person2New = new Person(BENSON);
        person2.setTags(tagSet2New);

        // check that tagToRemove from all persons are removed
        assertTrue(person1.equals(person1New));
        assertTrue(person2.equals(person2New));

        // check that tagSets are not equal because both are null
        assertFalse(person1.getTags().equals(null));
        assertFalse(person2.getTags().equals(null));
    }
```
