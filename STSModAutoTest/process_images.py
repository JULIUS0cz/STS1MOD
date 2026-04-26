import os
from PIL import Image

def process_images():
    src_dir = 'cards'
    dst_dir = 'AirTestCards'
    
    if not os.path.exists(dst_dir):
        os.makedirs(dst_dir)
        
    for filename in os.listdir(src_dir):
        if filename.lower().endswith(('.png', '.jpg', '.jpeg')):
            img_path = os.path.join(src_dir, filename)
            try:
                with Image.open(img_path) as img:
                    width, height = img.size
                    
                    # Calculate crop dimensions (1/2 size from center)
                    new_width = width / 2
                    new_height = height / 2
                    
                    left = (width - new_width) / 2
                    top = (height - new_height) / 2
                    right = (width + new_width) / 2
                    bottom = (height + new_height) / 2
                    
                    # Crop the image
                    cropped_img = img.crop((left, top, right, bottom))
                    
                    # Save to AirTestCards
                    save_path = os.path.join(dst_dir, filename)
                    cropped_img.save(save_path)
                    print(f"Processed: {filename}")
            except Exception as e:
                print(f"Error processing {filename}: {e}")

if __name__ == "__main__":
    process_images()
