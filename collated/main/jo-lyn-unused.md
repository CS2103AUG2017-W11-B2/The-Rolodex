# jo-lyn-unused
###### \java\seedu\address\model\ModelManager.java
``` java
    // Written as a warm up at the start of phase B and before features were finalised
    @Override
    public void removeTag(Tag tag) throws DuplicatePersonException, PersonNotFoundException {
        for (ReadOnlyPerson person: rolodex.getPersonList()) {
            Person newPerson = new Person(person);

            Set<Tag> newTags = new HashSet<>(newPerson.getTags());
            newTags.remove(tag);
            newPerson.setTags(newTags);

            rolodex.updatePerson(person, newPerson);
        }
    }
```
