package mk.ukim.finki.wp.lab.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UserFullnameConverter implements AttributeConverter<UserFullname, String> {
    @Override
    public String convertToDatabaseColumn(UserFullname userFullname) {
        return userFullname.getName() + " " + userFullname.getSurname();
    }

    @Override
    public UserFullname convertToEntityAttribute(String string) {
        String[] pieces = string.split(" ");
        UserFullname userFullname = new UserFullname();
        userFullname.setName(pieces[0]);
        userFullname.setSurname(pieces[1]);

        return userFullname;
    }
}