package br.com.erliss.compressor.compressor

import androidx.annotation.NonNull
import com.nicdahlquist.pngquant.LibPngQuant
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import java.io.File


/** CompressorPlugin */
class CompressorPlugin : FlutterPlugin, MethodCallHandler {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private lateinit var channel: MethodChannel

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "compressor")
        channel.setMethodCallHandler(this)
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        when {
            call.method.equals("compressImage") -> {
                val inputFile: String = call.argument("inputFile")!!
                val outputFile: String = call.argument("outputFile")!!
                val minQuality: Int = call.argument("minQuality")!!
                val maxQuality: Int = call.argument("maxQuality")!!
                val speed: Int = call.argument("speed")!!
                val floydDitherAmount: Float = call.argument("floydDitherAmount")!!

                val file = File(inputFile)
                if (!file.exists()) {
                    result.error("file does not exist", inputFile, null)
                    return
                }

                val resultCompact = LibPngQuant().pngQuantFile(
                    File(inputFile),
                    File(outputFile),
                    minQuality,
                    maxQuality,
                    speed,
                    floydDitherAmount
                )

                result.success(resultCompact)
                return
            }
            call.method == "getPlatformVersion" -> {
                result.success("Android ${android.os.Build.VERSION.RELEASE}")
            }
            else -> {
                result.notImplemented()
            }
        }
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }
}
