package marumasa.marumasa_sign.client;

import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.SignBlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class CustomSignBlockEntityRenderer extends SignBlockEntityRenderer {
    public CustomSignBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        super(ctx);
    }

    // renderメソッドをオーバーライドして レンダリング処理を変更する
    @Override
    public void render(SignBlockEntity sign, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (true) {
            // 看板URLから画像をレンダリングする

            // getEntityCutout で 透過対応の RenderLayer 生成
            final RenderLayer renderLayer = RenderLayer.getEntityCutout(new Identifier("test", "test"));
            render(renderLayer, matrices, vertexConsumers, light, overlay);

        } else {
            // 通常のMinecraft看板をレンダリングする

            // 親クラスのrenderメソッドを呼び出して 看板のレンダリング処理をする
            super.render(sign, tickDelta, matrices, vertexConsumers, light, overlay);
        }
    }
    public static void render(RenderLayer renderLayer, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {


        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(renderLayer);
        matrices.push();
        matrices.translate(0.5, 0.5, 0.5); // ブロックの中心に移動する
        matrices.scale(0.5f, 0.5f, 0.5f); // ブロックの半分の大きさにする
        Matrix4f matrix4f = matrices.peek().getPositionMatrix();
        Matrix3f matrix3f = matrices.peek().getNormalMatrix();
        vertexConsumer.vertex(matrix4f, -1.0F, -1.0F, -1.0F).color(255, 255, 255, 255).texture(0.0F, 0.0F).overlay(overlay).light(light).normal(matrix3f, 0.0F, 1.0F, 0.0F).next();
        vertexConsumer.vertex(matrix4f, -1.0F, -1.0F, 1.0F).color(255, 255, 255, 255).texture(0.0F, 1.0F).overlay(overlay).light(light).normal(matrix3f, 0.0F, 1.0F, 0.0F).next();
        vertexConsumer.vertex(matrix4f, 1.0F, -1.0F, 1.0F).color(255, 255, 255, 255).texture(1.0F, 1.0F).overlay(overlay).light(light).normal(matrix3f, 0.0F, 1.0F, 0.0F).next();
        vertexConsumer.vertex(matrix4f, 1.0F, -1.0F, -1.0F).color(255, 255, 255, 255).texture(1.0F, 0.0F).overlay(overlay).light(light).normal(matrix3f, 0.0F, 1.0F, 0.0F).next();
        matrices.pop();
    }


    @Override
    // ここで ブロックエンティティの表示範囲を設定できる
    public int getRenderDistance() {
        return 256;
    }
}
