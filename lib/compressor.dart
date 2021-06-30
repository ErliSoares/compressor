import 'dart:async';

import 'package:flutter/services.dart';

class Compressor {
  static const MethodChannel _channel = const MethodChannel('compressor');

  static Future<String?> get platformVersion async {
    final String? version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<bool?> compressImage(
      String inputFile,
      String outputFile, {
        int minQuality = 50,
        int maxQuality = 100,
        int speed = 1,
        double floydDitherAmount = 1,
  }) async {
    final bool? result = await _channel.invokeMethod(
      'compressImage',
      {
        'inputFile': inputFile,
        'outputFile': outputFile,
        'minQuality': minQuality,
        'maxQuality': maxQuality,
        'speed': speed,
        'floydDitherAmount': floydDitherAmount,
      },
    );
    return result;
  }
}
