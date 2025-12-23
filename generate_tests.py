import os
import glob
from openai import OpenAI

# 1. Setup the Client (Securely using GitHub Secrets)
client = OpenAI(api_key=os.environ.get("OPENAI_API_KEY"))

def generate_test_for_file(file_path):
    # Read the Java Source
    with open(file_path, 'r') as file:
        java_code = file.read()

    class_name = os.path.basename(file_path).replace(".java", "")
    print(f"Analyzing {class_name}...")

    # 2. The Research Prompt
    prompt = f"""
    You are an expert QA Engineer. Write a JUnit 5 test class for the following Java code.

    Requirements:
    1. Class Name: {class_name}Test
    2. Package: Match the source package.
    3. Use Mockito for dependencies.
    4. CRITICAL: Generate tests for EDGE CASES (nulls, boundary values, invalid formats).
    5. Output ONLY raw Java code. No Markdown.

    Code:
    {java_code}
    """

    try:
        # 3. Call GPT-4o
        response = client.chat.completions.create(
            model="gpt-4o",
            messages=[{"role": "user", "content": prompt}],
            temperature=0.2
        )

        test_code = response.choices[0].message.content

        # Clean up any potential markdown formatting
        test_code = test_code.replace("```java", "").replace("```", "").strip()

        # 4. Save the Test File
        test_file_path = file_path.replace("src/main/java", "src/test/java").replace(".java", "Test.java")
        os.makedirs(os.path.dirname(test_file_path), exist_ok=True)

        with open(test_file_path, 'w') as f:
            f.write(test_code)

        print(f"Generated: {test_file_path}")

    except Exception as e:
        print(f"Failed to generate for {class_name}: {e}")

# 5. Targeted Execution
# This prevents overwriting your Phase A manual work.
target_files = glob.glob('src/main/java/**/OrderService.java', recursive=True)

if target_files:
    for f in target_files:
        generate_test_for_file(f)
else:
    print("No 'OrderService.java' found. Waiting for code...")