# 🍕 Pizza Time - Java I/O Assignment

## Overview
Welcome to 1994! You're tasked with creating Winnipeg's first pizza delivery place with online ordering. This command-line application will handle address validation and delivery radius checking for pizza orders.

## Core Requirements (15 marks)
Create a Java application that:
1. Collects delivery information from users:
   - Street name
   - Street number
   - City
   - Postal code

2. Validates the delivery address against these criteria:
   - City must be "Winnipeg"
   - Street must be in the approved list: ["Burrows", "Main", "Henderson", "Magnus", "Mountain"]
   - Postal code must follow the format A1A 1A1 (letter-number-letter space number-letter-number)

3. Implements error handling:
   - Must handle incorrect data type inputs
   - Should re-prompt users when invalid data is entered
   - Must validate postal code format

4. Provides delivery status:
   - Clearly communicate whether the address is within the delivery radius

## Additional Features (Choose to earn full marks)

### Case-Insensitive Address Matching (1 mark)
- Implement case-insensitive comparison for street and city inputs
- Normalize input data before comparison

### Delivery Charge Calculation (2 marks)
- Calculate delivery fees based on street distance
- Implement a fixed fee structure per street

### Order Customization (2 marks)
- Add pizza size selection (Small, Medium, Large)
- Include topping selection options
- Display order summary with total cost

### Persistent Order History (3 marks)
- Save completed orders to a file
- Implement functionality to view order history
- Maintain persistent storage of order data

### Dynamic Delivery Area Expansion (2 marks)
- Allow addition of new streets to delivery area
- Maintain expanded delivery area during program execution

### Postal Code Area Validation (2 marks)
- Implement specific first-letter validation for postal codes
- Define acceptable postal code prefixes for delivery area

### Interactive Menu System (2 marks)
- Create navigable menu interface
- Implement back and forth navigation between options
- Include main menu, order customization, and order history views

## Helpful Code Snippets

### Character Operations
```java
// Get character at specific position
String text = "Hello";
char letter = text.charAt(2); // Gets 'l'

// Check if character is digit
char c = '5';
boolean isNumber = Character.isDigit(c); // Returns true

// Check if character is letter
char firstChar = text.charAt(0);
boolean isFirstCharLetter = Character.isLetter(firstChar); // Returns true
```

## Grading
- Base Implementation: 15 marks
- Additional Features: Up to 7 marks
- Total Possible: 20+ marks

## Requirements Checklist
- [ ] Basic address input and validation
- [ ] Postal code format verification
- [ ] Delivery radius checking
- [ ] Error handling
- [ ] User input re-prompting
- [ ] Selected additional features
- [ ] Code documentation
- [ ] Input validation
- [ ] Error messages
- [ ] Program output formatting

## Development Tips
1. Start with the core functionality before adding additional features
2. Test edge cases thoroughly
3. Implement robust error handling
4. Use meaningful variable names and add comments
5. Follow Java coding conventions
6. Test with various input scenarios