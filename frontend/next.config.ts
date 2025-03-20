import type { NextConfig } from "next";

const nextConfig: NextConfig = {
    /** @type {import('next').NextConfig} */
    images: {
        domains: ['maps.googleapis.com'],
    },
};

export default nextConfig;
