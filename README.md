# Prefix, Infix, and Postfix Calculator

Welcome to the Prefix, Infix, and Postfix Calculator project! This project is aimed at implementing a calculator capable of evaluating arithmetic expressions in three different formats: prefix, infix, and postfix.

## Overview

This project is designed to demonstrate the functionality of various expression evaluation techniques commonly used in computer science and mathematics. The calculator supports the following features:

- **Prefix Notation**: Expressions where the operator precedes the operands, e.g., `+ 2 3`.
- **Infix Notation**: Standard mathematical notation with operators placed between operands, e.g., `2 + 3`.
- **Postfix Notation**: Operators follow their operands, e.g., `2 3 +`.

## How it Works

The calculator employs a stack-based algorithm to evaluate expressions in all three notations. Here's a brief overview of the evaluation process:

1. **Tokenization**: The input expression is parsed into tokens (operands and operators).
2. **Conversion (Optional)**: Infix expressions may be converted to postfix or prefix notation for evaluation convenience.
3. **Evaluation**: The calculator iterates through the tokens, pushing operands onto the stack and performing operations when encountering operators.
4. **Result**: After processing all tokens, the final result is obtained from the stack.

## Getting Started

To use the calculator, follow these steps:

1. Clone the repository to your local machine.
2. Navigate to the project directory.
3. Compile the source code.
4. Run the calculator application.
5. Input the expression you want to evaluate.

## Example Usage

Here are some examples of how to use the calculator:

- Prefix notation: `+ 2 3`
- Infix notation: `2 + 3`
- Postfix notation: `2 3 +`
