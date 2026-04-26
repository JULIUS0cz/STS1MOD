# -*- encoding=utf8 -*-
__author__ = "Trae"

from airtest.core.api import *
import os

# 自动初始化，如果是在 Airtest IDE 中运行，它会自动连接当前设备
# if not cli_setup():
#     auto_setup(__file__, logdir=True, devices=["Windows:///"])

# 配置路径
BASE_DIR = os.path.dirname(os.path.dirname(__file__))
AIRTEST_CARDS_DIR = os.path.join(BASE_DIR, "AirTestCards")

# --- 模板图片配置 ---
# 注意：以下图片需要您在 Airtest IDE 中手动截取并保存到 sts_test.air 文件夹中
TEMP_COMPENDIUM = Template(r"compendium_btn.png", record_pos=(-0.3, 0.1), resolution=(1920, 1080)) # “百科大全”按钮
TEMP_CARD_LIBRARY = Template(r"card_library_btn.png", record_pos=(0.0, 0.0), resolution=(1920, 1080)) # “卡牌总览”按钮
TEMP_BACK_BTN = Template(r"back_btn.png", record_pos=(-0.45, -0.45), resolution=(1920, 1080)) # “返回”按钮（可选）

def safe_touch(template, name):
    """安全点击，如果找不到则打印提示"""
    pos = exists(template)
    if pos:
        touch(pos)
        return True
    else:
        print(f"提示: 未能识别到 [{name}] 按钮，请检查图片模板是否准确。")
        return False

def enter_compendium():
    """1. 进入百科大全"""
    print("正在尝试进入百科大全...")
    return safe_touch(TEMP_COMPENDIUM, "百科大全")

def enter_card_library():
    """2. 进入卡牌总览"""
    print("正在尝试进入卡牌总览...")
    return safe_touch(TEMP_CARD_LIBRARY, "卡牌总览")

def go_back():
    """4. 退回到卡牌总览界面"""
    # 优先尝试点击返回按钮，如果没截取则使用 ESC 键
    if exists(TEMP_BACK_BTN):
        touch(TEMP_BACK_BTN)
    else:
        keyevent("ESCAPE")
    sleep(1.5) # 等待动画过渡

def main():
    # 1. 进入百科大全
    if not enter_compendium():
        print("无法继续：未进入百科大全界面。")
        # return # 如果是调试阶段可以先注释掉

    sleep(1.0)

    # 2. 进入卡牌总览
    if not enter_card_library():
        print("无法继续：未进入卡牌总览界面。")
        # return

    sleep(1.0)

    # 获取待测试卡牌列表
    if not os.path.exists(AIRTEST_CARDS_DIR):
        print(f"错误: 找不到卡牌目录 {AIRTEST_CARDS_DIR}")
        return

    images = [f for f in os.listdir(AIRTEST_CARDS_DIR) if f.lower().endswith(('.png', '.jpg', '.jpeg'))]
    if not images:
        print("AirTestCards 文件夹中没有找到任何图片。")
        return

    print(f"--- 开始卡牌识别测试 (共 {len(images)} 张) ---")

    for img_name in images:
        full_path = os.path.join(AIRTEST_CARDS_DIR, img_name)
        # 创建卡牌模板，提高识别精度可以调整 threshold
        card_template = Template(full_path, threshold=0.8)
        
        print(f"正在匹配: {img_name}")
        
        # 3. 寻找并点击
        match_pos = exists(card_template)
        if match_pos:
            touch(match_pos)
            print(f"找到了[{img_name}]的卡牌")
            sleep(1.0)
            # 4. 退回到卡牌总览界面
            go_back()
        else:
            # 5. 如果不存在
            print(f"没有找到[{img_name}]的卡牌")

    print("--- 自动化测试任务已完成 ---")

if __name__ == "__main__":
    main()
