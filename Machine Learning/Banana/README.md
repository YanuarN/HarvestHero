# Banana Disease Detection - HarvestHero

This repository contains the essential files and resources for the HarvestHero Banana Disease Detection. The goal of this project is to accurately detect and classify various banana leaf diseases using machine learning models. Below is a detailed description of each file included in this folder:

## Descriptions

1. **HarvestHero_Banana_Disease_Detectionb.ipynb**
   - This Jupyter Notebook contains the complete workflow for training and evaluating the banana leaf disease detection model. It includes data preprocessing, model architecture, training process, and evaluation metrics.

2. **HarvestHero_Banana_ClassLabels.jpg**
   - An image file that visually represents the class labels used in the banana disease detection model. The class labels shown in the picture are:
     - Cordana: 0
     - Healthy: 1
     - Panama Disease: 2
     - Yellow and Black Sigatoka: 3
   - This can be used for quick reference and understanding of the different classes predicted by the model.

3. **Google Drive Link of banana_model.h5**
   - The pre-trained model file saved in HDF5 format. This model can be loaded and used for inference to detect banana leaf diseases. [(Click Here)](https://drive.google.com/drive/folders/1-3cyEZTXFgOo5MhZ9bucnq2YmwFObf7e?usp=drive_link)

4. **Google Drive Link of HarvestHero_banana_test_set.zip**
   - A zipped file containing the test set of banana leaf images. This dataset can be used to validate the model's performance on unseen data. [(Click Here)](https://drive.google.com/drive/folders/1dehw_TmbJ1-bfL8pntgqlmVv3cLWqUaL?usp=drive_link)

5. **Google Drive Link of tfjs_model.zip**
   - A zipped file containing the TensorFlow.js model. This allows the model to be deployed and used in web applications for real-time banana leaf disease detection. [(Click Here)](https://drive.google.com/drive/folders/1JCG7cHyE-Tu3VCcmB4Nsx_1iMSXrc5AK?usp=drive_link)

6. **Banana Disease Recommendation Action.xlsx**
   - An Excel spreadsheet that provides recommended actions for banana leaf diseases under various conditions. This includes specific actions based on weather and temperature conditions to manage and mitigate the diseases effectively.

7. **Banana Disease Detection Dataset**
   - [Rayhan Arlistya (2022). Banana Leaf Disease Dataset.](https://kaggle.com/datasets/f8181a55e97eb310f4d2bb2d755fb74ac4899f9a321f497ff979ee1e51b6577d)

## How to Use

1. **Running the Notebook:**
   - Open the `HarvestHero_Banana_Disease_Detectionb.ipynb` file in Jupyter Notebook or any compatible environment.
   - Follow the steps in the notebook to understand the data preprocessing, model training, and evaluation process.
   - Ensure you have all the necessary libraries installed as mentioned in the notebook.

2. **Using the Pre-trained Model:**
   - Load the `banana_model.h5` file in your preferred machine learning framework (e.g., TensorFlow, Keras).
   - Use the model to make predictions on new banana leaf images.

3. **Web Deployment:**
   - Extract the `tfjs_model.zip` file and follow the TensorFlow.js documentation to integrate the model into your web application.

4. **Test Set:**
   - Use the images in `HarvestHero_banana_test_set.zip` to test the performance of the model.
   - Evaluate the model’s accuracy, precision, recall, and other metrics using these test images.

5. **Disease Management:**
   - Refer to the `Banana Disease Recommendation Action.xlsx` file for actionable steps to manage banana leaf diseases based on different environmental conditions.

## Acknowledgments

This project was developed as part of the Bangkit Academy 2024 Capstone Project: "HarvestHero" initiative to aid banana farmers in early detection and management of leaf diseases. The dataset used for training the model was compiled from various reputable sources, ensuring a diverse and comprehensive collection of banana leaf disease images.

For any queries or further information, please contact the project maintainer.
