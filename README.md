# HyKore 🧩

> A platform to build plugins and mods on for Hytale. 🚀

HyKore sits between your code and the raw Hytale server API, so you can write plugins without wrestling the engine into submission. Write a handler, ask for what you need, and get on with the fun part. ✨

## What is this? 🤔

Hytale's server API is powerful. It is also... a lot. 😅

Events live in two completely different systems. Blocks have sixty-something fields. Death isn't even an event (it's a component, naturally 🙃). And half of it expects you to know what an archetype chunk is before you're allowed to react to someone breaking a block.

HyKore hides all of that. 🪄 You get a clean, friendly API to build against, it handles the messy wiring underneath, and it lets a bunch of plugins live happily together on one server. 🏡

Written a Bukkit plugin before? This'll feel like home. 🏠
Haven't? Even better, nothing to unlearn. 🎉

## A taste 🍿

```kotlin
class MyPlugin : HyKorePlugin {
    override fun setup(ctx: PluginContext) {
        ctx.registerEvents(BlockHandler())
    }

    override fun start(ctx: PluginContext) { }
    override fun teardown() { }
}

class BlockHandler {
    @OnEcsEvent
    fun onBreak(world: World, event: BreakBlockEvent) {
        world.say(Message("Someone broke a ${event.block.type}"))
    }
}
```

That's a working plugin. ☝️ No archetype chunks, no command buffers, no registering systems by hand. Just a method, an annotation, and the things you asked for as parameters. That's it. That's the tweet. 🐦

## Why HyKore? 💡

- 🍼 **Friendly by default.** Annotate a method, take what you need as parameters, done. Simple things stay simple.
- 💪 **Powerful when you need it.** The clean API is a thin layer over the real thing. Outgrow it? The full power is right there waiting.
- 🧱 **Plugins, not one giant mod.** Drop multiple plugins on one server and let them get along, instead of cramming everything into a single jar.
- 🔌 **Its own plugin loader and lifecycle.** HyKore loads, sets up, and tears down your plugins itself, independent of Hytale's own system. One HyKore plugin on the server, as many of yours on top as you like.
- 🛡️ **Wraps the scary bits.** Worlds, players, events and more come wrapped in a tidy API, so a Hytale update doesn't automatically mean a plugin rewrite.

## Status 🚧

Early days, and moving fast. 🏃💨 Hytale itself is in early access, so expect things to wobble and change as we go. If you like building on fresh ground, you're in exactly the right place. 🌱

## Getting started 🛠️

Coming soon! For now, poke around the examples and say hello. 👋

## Contributing 🤝

HyKore gets better with more hands. 🙌 Wrapping the whole of Hytale is a big job, and the whole thing is designed so you can add one piece without needing to understand the entire machine. Want to help shape the platform while it's young? Jump in. 🏊

## Licence 📜

MIT, See [License](./LICENSE.md)